package com.example.chat.server;

import com.example.chat.DiffHelman.DiffHellman;
import com.example.chat.kafka.KafkaWriter;
import com.example.chat.model.Message;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final KafkaWriter kafkaWriter = new KafkaWriter();

    //проверяем есть ли такая вообще комната
    private static final Map<Long, Pair<Long, Long>> roomsConnection = new HashMap<>(); // {key: roomId, value: {leftClientId, rightClientId}}
    private static final Map<Long, BigInteger[]> roomsParameter = new HashMap<>(); // {key: roomId, value: roomParameters[]}

    public synchronized BigInteger[] connect(long clientId, long roomId) {
        if (roomsConnection.containsKey(roomId)) {
            Pair<Long, Long> room = roomsConnection.get(roomId);
            BigInteger[] parameters = roomsParameter.get(roomId);

            if (room.getLeft() == null || room.getRight() == null) {
                // TODO CHECK
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
}
