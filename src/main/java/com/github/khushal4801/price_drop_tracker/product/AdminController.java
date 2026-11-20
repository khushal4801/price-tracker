package com.github.khushal4801.price_drop_tracker.product;

import com.github.khushal4801.price_drop_tracker.checker.PriceCheckJob;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PriceCheckJob job;

    @PostMapping("/check-now")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void checkNow() { job.run(); }
}