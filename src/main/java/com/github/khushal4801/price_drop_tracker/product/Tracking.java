package com.github.khushal4801.price_drop_tracker.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "trackings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "product_id"}))
@Getter @Setter @NoArgsConstructor
public class Tracking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal targetPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "tracking_channels", joinColumns = @JoinColumn(name = "tracking_id"))
    @Column(name = "channel")
    private Set<Channel> channels = new HashSet<>();

    @Column(nullable = false)
    private boolean active = true;

    public record TrackingRequest(
            @NotNull Long userId,
            @NotNull Long productId,
            @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal targetPrice,
            @NotEmpty Set<Channel> channels) {}

    public record TrackingUpdateRequest(
            @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal targetPrice,
            @NotEmpty Set<Channel> channels) {}

    public record TrackingResponse(Long id, Long userId, Long productId, String productName,
                                   BigDecimal targetPrice, Set<Channel> channels, boolean active) {
        public static TrackingResponse from(Tracking t) {
            return new TrackingResponse(t.getId(), t.getUser().getId(), t.getProduct().getId(),
                    t.getProduct().getName(), t.getTargetPrice(), t.getChannels(), t.isActive());
        }
    }
}