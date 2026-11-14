package com.github.khushal4801.price_drop_tracker.product.services;

import com.github.khushal4801.price_drop_tracker.common.*;
import com.github.khushal4801.price_drop_tracker.product.User;
import com.github.khushal4801.price_drop_tracker.product.dto.*;
import com.github.khushal4801.price_drop_tracker.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository users;

    @Transactional
    public UserResponse create(UserRequest r) {
        if (users.existsByEmail(r.email())) {
            throw new ConflictException("Email already registered: " + r.email());
        }
        User u = new User();
        u.setName(r.name());
        u.setEmail(r.email());
        u.setPhone(r.phone());
        return UserResponse.from(users.save(u));
    }

    @Transactional(readOnly = true)
    public UserResponse get(Long id) {
        return users.findById(id).map(UserResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }
}
