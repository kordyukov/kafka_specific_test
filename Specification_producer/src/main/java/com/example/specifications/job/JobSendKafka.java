package com.example.specifications.job;

import com.example.specifications.kafka.KafkaUserProducer;
import com.example.specifications.model.dto.UserCustomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobSendKafka {
    private final KafkaUserProducer producer;

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(
            name = "TaskScheduler_receiveMessageTask",
            lockAtLeastFor = "PT30S",
            lockAtMostFor = "PT55S")
    public void receiveMessage() {
        log.info("Start JobDbUpdated!");
        IntStream.range(1, 50)
                .forEach(i -> {
                    producer.produce(new UserCustomDto(
                            "first_name_%s".formatted(UUID.randomUUID()),
                            "last_name_%s".formatted(UUID.randomUUID()),
                            "email_%s".formatted(UUID.randomUUID()),
                            25));
                });


    }
}
