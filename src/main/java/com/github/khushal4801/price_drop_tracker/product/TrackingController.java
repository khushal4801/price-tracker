package com.github.khushal4801.price_drop_tracker.product;

import com.github.khushal4801.price_drop_tracker.product.dto.*;
import com.github.khushal4801.price_drop_tracker.product.services.TrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrackingController {
    private final TrackingService service;

    @PostMapping("/trackings")
    public ResponseEntity<TrackingResponse> create(@Valid @RequestBody TrackingRequest request) {
        TrackingResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/trackings/" + created.id())).body(created);
    }

    @GetMapping("/users/{userId}/trackings")
    public List<TrackingResponse> listForUser(@PathVariable Long userId) {
        return service.listForUser(userId);
    }

    @PutMapping("/trackings/{id}")
    public TrackingResponse update(@PathVariable Long id, @Valid @RequestBody TrackingUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/trackings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}