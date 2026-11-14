package com.github.khushal4801.price_drop_tracker.product.services;
import com.github.khushal4801.price_drop_tracker.common.*;
import com.github.khushal4801.price_drop_tracker.product.*;
import com.github.khushal4801.price_drop_tracker.product.dto.*;
import com.github.khushal4801.price_drop_tracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TrackingService {
    private final TrackingRepository trackings;
    private final UserRepository users;
    private final ProductRepository products;

    @Transactional
    public TrackingResponse create(TrackingRequest r) {
        User user = users.findById(r.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + r.userId()));
        Product product = products.findById(r.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + r.productId()));
        if (trackings.existsByUserIdAndProductId(user.getId(), product.getId())) {
            throw new ConflictException("User already tracks this product");
        }
        Tracking t = new Tracking();
        t.setUser(user);
        t.setProduct(product);
        t.setTargetPrice(r.targetPrice());
        t.setChannels(new HashSet<>(r.channels()));
        return TrackingResponse.from(trackings.save(t));
    }

    @Transactional(readOnly = true)
    public List<TrackingResponse> listForUser(Long userId) {
        if (!users.existsById(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
        return trackings.findByUserId(userId).stream().map(TrackingResponse::from).toList();
    }

    @Transactional
    public TrackingResponse update(Long id, TrackingUpdateRequest r) {
        Tracking t = trackings.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tracking not found: " + id));
        t.setTargetPrice(r.targetPrice());
        t.setChannels(new HashSet<>(r.channels()));
        return TrackingResponse.from(t);
    }

    @Transactional
    public void delete(Long id) {
        if (!trackings.existsById(id)) {
            throw new ResourceNotFoundException("Tracking not found: " + id);
        }
        trackings.deleteById(id);
    }
}