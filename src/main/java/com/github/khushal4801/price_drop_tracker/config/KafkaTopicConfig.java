package com.github.khushal4801.price_drop_tracker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class KafkaTopicConfig {

    @Bean
    public NewTopic priceCheckedTopic() {
        return TopicBuilder.name(Topics.PRICE_CHECKED).partitions(3).replicas(1).build();
    }
}