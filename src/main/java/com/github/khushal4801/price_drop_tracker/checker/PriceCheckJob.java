package com.github.khushal4801.price_drop_tracker.checker;

import com.github.khushal4801.price_drop_tracker.config.Topics;
import com.github.khushal4801.price_drop_tracker.events.PriceCheckedEvent;
import com.github.khushal4801.price_drop_tracker.product.Product;
import com.github.khushal4801.price_drop_tracker.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceCheckJob {
    private final ProductRepository products;
    private final PriceFetcher fetcher;
    private final KafkaTemplate<String, PriceCheckedEvent> kafka;

    @Scheduled(cron = "${app.price-check.cron}")
    public void run() {
        List<Product> active = products.findByActiveTrue();
        log.info("Price check started for {} active products", active.size());

        for (Product p : active) {
            PriceCheckedEvent event = PriceCheckedEvent.of(p.getId(), fetcher.fetch(p));
            kafka.send(Topics.PRICE_CHECKED, p.getId().toString(), event)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Failed to publish eventId={} productId={}", event.eventId(), p.getId(), ex);
                        } else {
                            log.debug("Published eventId={} partition={} offset={}", event.eventId(),
                                    result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
                        }
                    });
        }
    }
}