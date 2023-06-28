package com.example.specifications.kafka;

import com.example.specifications.mapper.UserCustomMapper;
import com.example.specifications.model.dto.UserCustomDto;
import com.example.specifications.service.UserCustomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaUserConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final UserCustomService userCustomService;
    private final ObjectMapper objectMapper;
    private final UserCustomMapper userCustomMapper;
    @Value("${spring.kafka.topic-names.input.users}")
    private String TOPIC;

    public void consume() {
        consumer.subscribe(Collections.singletonList(TOPIC));
        // run an infinite loop where we consume and print new messages to the topic
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("received message: %s\n", record.value());
            try {
                userCustomService.save(userCustomMapper.toEntity(
                        objectMapper.readValue(record.value(), UserCustomDto.class)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
