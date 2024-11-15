package org.example.events;


import lombok.*;
import org.example.dtos.CabTypes;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CabEvent {

    private UUID driverId;
    private String registrationNumber;
    private CabTypes cabTypes;
}
