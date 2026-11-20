package com.github.khushal4801.price_drop_tracker.events;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PriceCheckedEvent(
        String eventId,
        Long productId,
        BigDecimal price,
        Instant checkedAt) {

    public static PriceCheckedEvent of(Long productId, BigDecimal price) {
        return new PriceCheckedEvent(UUID.randomUUID().toString(), productId, price, Instant.now());
    }
}