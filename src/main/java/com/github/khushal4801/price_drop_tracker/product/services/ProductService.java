package com.github.khushal4801.price_drop_tracker.product.services;

import com.github.khushal4801.price_drop_tracker.common.*;
import com.github.khushal4801.price_drop_tracker.product.Product;
import com.github.khushal4801.price_drop_tracker.product.dto.*;
import com.github.khushal4801.price_drop_tracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository products;
    private final TrackingRepository trackings;

    @Transactional
    public ProductResponse create(ProductRequest r) {
        Product p = new Product();
        p.setName(r.name());
        p.setUrl(r.url());
        p.setCurrentPrice(r.currentPrice());
        return ProductResponse.from(products.save(p));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> list(Boolean active, Pageable pageable) {
        Page<Product> page = (active == null) ? products.findAll(pageable)
                : products.findByActive(active, pageable);
        return page.map(ProductResponse::from);
    }

    @Transactional(readOnly = true)
    public ProductResponse get(Long id) {
        return ProductResponse.from(find(id));
    }

    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest r) {
        Product p = find(id);
        p.setName(r.name());
        p.setUrl(r.url());
        return ProductResponse.from(p);   // dirty checking saves it
    }

    @Transactional
    public ProductResponse setActive(Long id, boolean active) {
        Product p = find(id);
        p.setActive(active);
        return ProductResponse.from(p);
    }

    @Transactional
    public void delete(Long id) {
        Product p = find(id);
        if (trackings.existsByProductId(id)) {
            throw new ConflictException("Product has trackings; pause it instead of deleting");
        }
        products.delete(p);
    }

    private Product find(Long id) {
        return products.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }
}