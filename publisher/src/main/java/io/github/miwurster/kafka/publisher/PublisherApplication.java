package io.github.miwurster.kafka.publisher;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@EnableKafka
@SpringBootApplication
public class PublisherApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(PublisherApplication.class);

    public static void main(final String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }

    // -- Main program

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    public PublisherApplication(final KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(final String... strings) throws Exception {
        while (true) {
            ListenableFuture<SendResult<Integer, String>> f;

            f = kafkaTemplate
                    .send("my.awesome.hello", "Hello");
            f.get();
            logger.info("1. message sent? {}", f.isDone());

            f = kafkaTemplate
                    .send("my.awesome.world", "World.");
            f.get();
            logger.info("2. message sent? {}", f.isDone());

            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        }
    }
}
