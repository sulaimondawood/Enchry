package com.dawood.enchry.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Climate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String sensoredData;

    private String nonce;

    private String salt;

    private String info;

    private String time;

    private String timezone;

    private Long longitude;

    private Long latitude;

    private Long generationtimeMs;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

}
