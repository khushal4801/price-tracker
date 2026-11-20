package com.github.khushal4801.price_drop_tracker.analyzer;

import com.github.khushal4801.price_drop_tracker.config.Topics;
import com.github.khushal4801.price_drop_tracker.events.PriceCheckedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PriceCheckedConsumer {

    @KafkaListener(topics = Topics.PRICE_CHECKED, groupId = "analyzer-group")
    public void onMessage(PriceCheckedEvent event,
                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                          @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Received eventId={} productId={} price={} partition={} offset={}",
                event.eventId(), event.productId(), event.price(), partition, offset);
    }
}