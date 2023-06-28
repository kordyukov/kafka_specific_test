package com.example.specifications.job;

import com.example.specifications.kafka.KafkaUserConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobDbUpdated {
    private final KafkaUserConsumer consumer;

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(
            name = "TaskScheduler_receiveMessageTask",
            lockAtLeastFor = "PT30S",
            lockAtMostFor = "PT55S")
    public void receiveMessage() {
        log.info("Start JobDbUpdated!");
        consumer.consume();
    }
}
