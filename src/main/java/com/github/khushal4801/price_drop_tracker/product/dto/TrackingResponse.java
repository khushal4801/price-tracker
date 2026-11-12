package com.github.khushal4801.price_drop_tracker.product.dto;

import com.github.khushal4801.price_drop_tracker.product.Channel;
import com.github.khushal4801.price_drop_tracker.product.Tracking;

import java.math.BigDecimal;
import java.util.Set;

public record TrackingResponse(Long id, Long userId, Long productId, String productName,
                               BigDecimal targetPrice, Set<Channel> channels, boolean active) {
    public static TrackingResponse from(Tracking t) {
        return new TrackingResponse(t.getId(), t.getUser().getId(), t.getProduct().getId(),
                t.getProduct().getName(), t.getTargetPrice(), t.getChannels(), t.isActive());
    }
}
