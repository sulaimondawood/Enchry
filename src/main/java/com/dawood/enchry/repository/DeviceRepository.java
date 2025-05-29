package com.dawood.enchry.repository;

import com.dawood.enchry.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
}
