package com.dawood.enchry.repository;

import com.dawood.enchry.model.Device;
import com.dawood.enchry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findByUser(User user);

    Optional<Device> findByIdAndUser(UUID id, User user);
}
