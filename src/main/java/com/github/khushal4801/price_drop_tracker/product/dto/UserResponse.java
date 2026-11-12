package com.github.khushal4801.price_drop_tracker.product.dto;

import com.github.khushal4801.price_drop_tracker.product.User;

import java.time.Instant;

public record UserResponse(Long id, String name, String email, String phone, Instant createdAt) {
    public static UserResponse from(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getPhone(), u.getCreatedAt());
    }
}