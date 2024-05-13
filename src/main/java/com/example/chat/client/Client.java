package com.example.chat.client;

import com.example.chat.RC6.Cipher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.chat.kafka.KafkaWriter;
import lombok.extern.slf4j.Slf4j;
import com.example.chat.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import com.example.chat.server.Server;

import java.math.BigInteger;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Client {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final KafkaWriter kafkaWriter = new KafkaWriter();
    private final long clientId;
    private final Server server;
    private Cipher cipher;
    private static final Map<Long, Boolean> isRunningRoom = new HashMap<>();
    private BigInteger privateKey;
    private BigInteger p;
    private BigInteger g;

    public Client(Server server, Cipher cipher, long clientId) {
        this.server = server;
        this.cipher = cipher;
        this.clientId = clientId;
    }

    public void start(long roomId) {
        BigInteger[] roomParameters = server.connect(clientId, roomId);
        p = roomParameters[0];
        g = roomParameters[1];

        if (roomParameters != null) {
            service.submit(() -> startKafka(getTopicName(clientId, roomId), roomId));
        } else {
            System.out.println("Error connection");
        }
    }

    public void startKafka(String inputTopic, long roomId) {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093",
                        ConsumerConfig.GROUP_ID_CONFIG, "group_" + clientId,
                        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"
                ),
                new StringDeserializer(),
                new StringDeserializer()
        );

        kafkaConsumer.subscribe(Collections.singleton(inputTopic));

        try {
            while (Boolean.TRUE.equals(isRunningRoom.getOrDefault(roomId, Boolean.TRUE))) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    String jsonMessage = consumerRecord.value();

                    if (jsonMessage.contains("ID")) {
                        Message message = objectMapper.readValue(jsonMessage, Message.class);
                        privateKey = generatePrivateKey();
                        BigInteger clientPublicKey = generatePublicKey(privateKey);

                        kafkaWriter.send(
                                Message.builder()
                                        .typeMessage("PUBLIC_KEY")
                                        .clientId(clientId)
                                        .publicKey(clientPublicKey.toByteArray())
                                        .build()
                                        .toString(),
                                getTopicName(message.getClientId(), roomId)
                        );
                        // генерируешь свой публичный ключ и отправляешь его другому клиенту

                    } else if (jsonMessage.contains("PUBLIC_KEY")) {
                        Message message = objectMapper.readValue(jsonMessage, Message.class);

                        // ты получил публичный ключ другого клиента и теперь можешь получить ключ для шифрования и дешифрования
                         BigInteger key = new BigInteger(message.getPublicKey()).modPow(privateKey, p);
                         log.info("КЛЮЧ ------------ " + key);
                    } else { //TEXT
                        //todo
                        byte[] textToDecrypt = jsonMessage.getBytes();
                        String decryptedMessage = new String(cipher.decrypt(textToDecrypt));
                        //и на фронт чапа чопа

                        System.out.println("Received message: " + decryptedMessage);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Error while working kafka reader", ex);
        }

        kafkaConsumer.close();
    }

    public void sendMessage(String message, long roomId, long clientId) {
        String outputTopic = getTopicName(clientId, roomId);
        log.info("сообщение отправляется ------------ " + message);
        kafkaWriter.send(message, outputTopic);
    }

    public String getTopicName(long clientId, long roomId) {
        return "input_" + clientId + "_" + roomId;
    }


    public BigInteger generatePublicKey(BigInteger privateKey) {
        BigInteger publicKey = g.modPow(privateKey,p);
        return publicKey;
    }

    //первый
    public BigInteger generatePrivateKey() {
        privateKey = new BigInteger(100, new Random());
        return privateKey;
    }

    public void close(long roomId) {
        isRunningRoom.put(roomId, Boolean.FALSE);
    }
}