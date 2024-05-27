package com.example.chat.server;

import com.example.chat.DiffHelman.DiffHellman;
import com.example.chat.entity.CipherData;
import com.example.chat.entity.UserData;
import com.example.chat.kafka.KafkaWriter;
import com.example.chat.model.Message;
import com.example.chat.repository.CipherRepository;
import com.example.chat.repository.RoomRepository;
import com.example.chat.repository.UserRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class Server {
    private static final KafkaWriter kafkaWriter = new KafkaWriter();

    //проверяем есть ли такая вообще комната
    private static final Map<Long, Pair<Long, Long>> roomsConnection = new HashMap<>(); // {key: roomId, value: {leftClientId, rightClientId}}
    private static final Map<Long, BigInteger[]> roomsParameter = new HashMap<>(); // {key: roomId, value: roomParameters[]}

    private CipherRepository cipherRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;

    public synchronized BigInteger[] connect(long clientId, long roomId) {
        if (roomsConnection.containsKey(roomId)) {
            Pair<Long, Long> room = roomsConnection.get(roomId);
            BigInteger[] parameters = roomsParameter.get(roomId);

            if (room.getLeft() == null || room.getRight() == null) {
                long firstClientId = room.getLeft() == null ? room.getRight() : room.getLeft();
                roomsConnection.put(roomId, Pair.of(clientId, firstClientId));
                startRoom(firstClientId, clientId, roomId);
                return parameters;
            }
        } else {
            BigInteger[] parameters = DiffHellman.generatePages(); // TODO из дефа хелмана параметры
            roomsParameter.put(roomId, parameters);
            roomsConnection.put(roomId, Pair.of(clientId, null));
            return parameters;
        }

        return null;
    }

    public void startRoom(long firstClientId, long secondClientId, long roomId) {

        String outputTopic1 = getTopicName(firstClientId, roomId);
        String outputTopic2 = getTopicName(secondClientId, roomId);

        kafkaWriter.send(
                Message.builder()
                        .typeMessage("ID")
                        .clientId(secondClientId)
                        .build()
                        .toString(),
                outputTopic1
        );
        kafkaWriter.send(
                Message.builder()
                        .typeMessage("ID")
                        .clientId(firstClientId)
                        .build()
                        .toString(),
                outputTopic2
        );
    }


    public String getTopicName(long clientId, long roomId) {
        return "input_" + clientId + "_" + roomId;
    }

    public synchronized UserData login(String name, String nameAlgorithm) {
        CipherData cipherData;
        if ("RC6".equals(nameAlgorithm)) {
            cipherData = CipherData.builder()
                    .name("RC6")
                    .build();
        } else if ("Serpent".equals(nameAlgorithm)) {
            cipherData = CipherData.builder()
                    .name("Serpent")
                    .build();
        } else {
            throw new IllegalStateException("Something wrong with login");
        }

        cipherData = cipherRepository.save(cipherData);
        return userRepository.save(UserData.builder()
                .name(name)
                .cipherId(cipherData.getId())
                .rooms(new long[0])
                .build()
        );
    }

}
