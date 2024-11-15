package org.example.service;

import org.example.dtos.CommonStatus;
import org.example.dtos.DriverDTO;
import org.example.entity.Driver;
import org.example.events.CabEvent;
import org.example.repository.DriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    public   DriveRepository driveRepository;

    @Autowired
    private KafkaTemplate<String,CabEvent> cabEventkafkaTemplate;



    public String saveDriver(DriverDTO driverDTO) {
        Driver driver= driveRepository.save(Driver.builder()
                        .driverName(driverDTO.getDriverName())
                        .driverEmail(driverDTO.getDriverEmail())
                        .driverLocation(driverDTO.getDriverLocation())
                        .driverStatus(CommonStatus.PENDING)
                .build());

        CabEvent cabEvent=CabEvent.builder()
                .driverId(driver.getDriverId())
                .cabTypes(driverDTO.getCabDTO().getCabType())
                .registrationNumber(driverDTO.getCabDTO().getRegistrationNumber())
                .build();
        cabEventkafkaTemplate.send("add-cab-event",cabEvent);
        return "Driver Details::"+driver.getDriverId()+"is processed";
    }

    public List<Driver> getAllDrivers() {
        return driveRepository.findAll();
    }
}
