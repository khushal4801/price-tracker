package com.github.khushal4801.price_drop_tracker.product.dto;

import com.github.khushal4801.price_drop_tracker.product.Channel;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Set;

public record TrackingUpdateRequest(
        @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal targetPrice,
        @NotEmpty Set<Channel> channels) {}

