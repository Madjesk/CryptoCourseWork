package com.example.chat.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;

public class KafkaWriter {
    private final KafkaProducer<String, String> kafkaProducer;

    public KafkaWriter() {
        this.kafkaProducer = new KafkaProducer<>(
                Map.of(
                        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093",
                        ProducerConfig.CLIENT_ID_CONFIG, "producerKafkaWriter"
                ),
                new StringSerializer(),
                new StringSerializer()
        );
    }

    public void send(String message, String outputTopic) {
        kafkaProducer.send(new ProducerRecord<>(
                outputTopic,
                message
        ));
    }
}
