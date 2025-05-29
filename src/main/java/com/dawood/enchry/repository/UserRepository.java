package com.dawood.enchry.repository;

import com.dawood.enchry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UUID, User> {
    Optional<User> findByEmail(String email);
}
