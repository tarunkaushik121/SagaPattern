package org.example.events;

import lombok.*;
import org.example.dtos.CommonStatus;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateDriverStatusEvent {

    private UUID driverID;
    private CommonStatus driverStatus;
}
