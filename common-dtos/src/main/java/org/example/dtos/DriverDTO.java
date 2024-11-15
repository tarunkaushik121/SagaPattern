package org.example.dtos;

import lombok.Data;

@Data
public class DriverDTO {

    private String DriverName;
    private String DriverEmail;
    private String DriverLocation;

    private CabDTO cabDTO;


}
