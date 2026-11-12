package com.github.khushal4801.price_drop_tracker.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductUpdateRequest(
        @NotBlank @Size(max = 200) String name,
        @NotBlank @Size(max = 1000) @Pattern(regexp = "^https?://.+") String url) {}

