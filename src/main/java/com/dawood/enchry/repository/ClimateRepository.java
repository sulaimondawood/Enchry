package com.dawood.enchry.repository;

import com.dawood.enchry.model.Climate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClimateRepository extends JpaRepository<Climate, UUID> {
    Optional<Climate> findTopByDeviceUserEmailOrderByTimeDesc(String username);
}
