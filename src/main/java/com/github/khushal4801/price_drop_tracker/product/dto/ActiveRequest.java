package com.github.khushal4801.price_drop_tracker.product.dto;

import jakarta.validation.constraints.NotNull;

public record ActiveRequest(@NotNull Boolean active) {}

