package com.github.khushal4801.price_drop_tracker.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank @Size(max = 200) String name,
        @NotBlank @Size(max = 1000) @Pattern(regexp = "^https?://.+", message = "must start with http:// or https://")
        String url,
        @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal currentPrice) {}

