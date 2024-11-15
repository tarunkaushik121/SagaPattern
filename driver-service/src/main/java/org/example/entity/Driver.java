package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dtos.CommonStatus;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="driverId")
    private UUID driverId;
    @Column(name="driverName")
    private String driverName;
    @Column(name="driverEmail")
    private String driverEmail;
    @Column(name="driverLocation")
    private String driverLocation;
    @Enumerated(EnumType.STRING)
    @Column(name = "driver_status")
    private CommonStatus driverStatus;
}