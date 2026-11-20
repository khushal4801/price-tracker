package com.github.khushal4801.price_drop_tracker.checker;

import com.github.khushal4801.price_drop_tracker.product.Product;

import java.math.BigDecimal;

public interface PriceFetcher {
    BigDecimal fetch(Product product);
}