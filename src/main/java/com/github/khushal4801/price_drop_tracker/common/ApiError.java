package com.github.khushal4801.price_drop_tracker.common;

import java.time.Instant;
import java.util.*;

public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fieldErrors) {}