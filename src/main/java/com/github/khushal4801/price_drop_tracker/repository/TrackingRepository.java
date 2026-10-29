package com.github.khushal4801.price_drop_tracker.repository;

import com.github.khushal4801.price_drop_tracker.product.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findByUserId(Long userId);
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    // used in Sprint 2 by the analyzer
    List<Tracking> findByProductIdAndActiveTrueAndTargetPriceGreaterThanEqual(Long productId, BigDecimal price);
}
