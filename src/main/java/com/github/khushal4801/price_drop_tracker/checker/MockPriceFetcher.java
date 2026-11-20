package com.github.khushal4801.price_drop_tracker.checker;

import com.github.khushal4801.price_drop_tracker.product.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MockPriceFetcher implements PriceFetcher {

    @Override
    public BigDecimal fetch(Product product) {
        // random price within ±20% of the stored price, never below 1.00
        double factor = 0.8 + ThreadLocalRandom.current().nextDouble() * 0.4;
        BigDecimal price = product.getCurrentPrice()
                .multiply(BigDecimal.valueOf(factor))
                .setScale(2, RoundingMode.HALF_UP);
        return price.max(new BigDecimal("1.00"));
    }
}