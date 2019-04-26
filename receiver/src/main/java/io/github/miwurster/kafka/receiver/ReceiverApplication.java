package io.github.miwurster.kafka.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@EnableKafka
@SpringBootApplication
public class ReceiverApplication {

    private static Logger logger = LoggerFactory.getLogger(ReceiverApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

    @KafkaListener(topicPattern = "my.awesome.*")
    public void receive(@Payload final String value,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) final Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) final String topic,
                        @Header(KafkaHeaders.OFFSET) final Long offset) {
        logger.info("Received value \"{}\", partition={}, topic={}, offset={}", value,
                partition, topic, offset);
    }
}
