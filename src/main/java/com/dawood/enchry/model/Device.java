package com.dawood.enchry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "device")
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Device name is required")
    private String deviceName;

    @Column(nullable = true)
    private String deviceDescription;

    @Column(nullable = false)
    @NotBlank(message = "Select sensor type")
    private String sensorType;

    private String devicePublicId;

    @Column(nullable = false)
    private boolean isActive = false;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "device")
    private List<Climate> climate;
}
