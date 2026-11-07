package com.github.khushal4801.price_drop_tracker.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal currentPrice;

    @Column(nullable = false)
    private boolean active = true;

    private Instant lastCheckedAt;

    public record ProductRequest(
            @NotBlank @Size(max = 200) String name,
            @NotBlank @Size(max = 1000) @Pattern(regexp = "^https?://.+", message = "must start with http:// or https://")
            String url,
            @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal currentPrice) {}

    public record ProductUpdateRequest(
            @NotBlank @Size(max = 200) String name,
            @NotBlank @Size(max = 1000) @Pattern(regexp = "^https?://.+") String url) {}

    public record ActiveRequest(@NotNull Boolean active) {}

    public record ProductResponse(Long id, String name, String url, BigDecimal currentPrice,
                                  boolean active, Instant lastCheckedAt) {
        public static ProductResponse from(Product p) {
            return new ProductResponse(p.getId(), p.getName(), p.getUrl(), p.getCurrentPrice(),
                    p.isActive(), p.getLastCheckedAt());
        }
    }
}