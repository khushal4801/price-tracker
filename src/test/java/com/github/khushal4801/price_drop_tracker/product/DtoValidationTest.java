package com.github.khushal4801.price_drop_tracker.product;

import com.github.khushal4801.price_drop_tracker.product.dto.TrackingRequest;
import com.github.khushal4801.price_drop_tracker.product.dto.UserRequest;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class DtoValidationTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void invalidUserIsRejected() {
        var bad = new UserRequest("", "not-an-email", "abc");
        assertEquals(3, validator.validate(bad).size());
    }

    @Test
    void validUserPasses() {
        var ok = new UserRequest("Asha", "asha@example.com", "+919876543210");
        assertTrue(validator.validate(ok).isEmpty());
    }

    @Test
    void negativeTargetPriceIsRejected() {
        var bad = new TrackingRequest(1L, 1L, new BigDecimal("-5"), Set.of(Channel.EMAIL));
        assertFalse(validator.validate(bad).isEmpty());
    }

    @Test
    void emptyChannelsIsRejected() {
        var bad = new TrackingRequest(1L, 1L, new BigDecimal("99.99"), Set.of());
        assertFalse(validator.validate(bad).isEmpty());
    }
}