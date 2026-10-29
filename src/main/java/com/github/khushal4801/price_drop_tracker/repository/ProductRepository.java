package com.github.khushal4801.price_drop_tracker.repository;

import com.github.khushal4801.price_drop_tracker.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();
    Page<Product> findByActive(boolean active, Pageable pageable);
}
