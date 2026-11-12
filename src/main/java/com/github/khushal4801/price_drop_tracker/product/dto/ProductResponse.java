package com.github.khushal4801.price_drop_tracker.product.dto;
import com.github.khushal4801.price_drop_tracker.product.Product;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(Long id, String name, String url, BigDecimal currentPrice,
                              boolean active, Instant lastCheckedAt) {
    public static ProductResponse from(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getUrl(), p.getCurrentPrice(),
                p.isActive(), p.getLastCheckedAt());
    }
}