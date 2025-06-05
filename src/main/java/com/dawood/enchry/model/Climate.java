package com.dawood.enchry.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Climate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Sensored data is required")
    private String sensoredData;

    @NotBlank(message = "Nonce is required")
    private String nonce;

    private String salt;

    @NotBlank(message = "Time is required")
    private String time;

    @NotBlank(message = "Timezone wasn't specified")
    private String timezone;

    @NotEmpty(message = "Longitude is required")
    private Long longitude;

    @NotEmpty(message = "Latitude is required")
    private Long latitude;

    @CreatedDate
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

}
