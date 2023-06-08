package com.example.specifications.kafka;

import com.example.specifications.model.dto.UserCustomDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaUserProducer {
    private final KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;
    @Value("${spring.kafka.topic-names.output.users-action}")
    private String TOPIC;

    public void produce(UserCustomDto message) {
        var payload = getPayload(message);
        ProducerRecord<String, String> record =
                new ProducerRecord<>(TOPIC, getPayload(message));
        log.debug(
                "Send record with payload {} to kafka... to topic {}",
                payload,
                TOPIC);

        kafkaProducer.send(
                record,
                (recordMetadata, e) -> {
                    log.info("send to kafka %s".formatted(payload));
                });
    }

    private String getPayload(UserCustomDto message) {
        String payload = null;
        try {
            payload =
                    objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        return payload;
    }
}
