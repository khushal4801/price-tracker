package com.github.khushal4801.price_drop_tracker.repository;

import com.github.khushal4801.price_drop_tracker.product.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}

