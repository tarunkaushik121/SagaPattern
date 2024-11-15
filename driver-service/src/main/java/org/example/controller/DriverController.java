package org.example.controller;

import org.example.dtos.DriverDTO;
import org.example.entity.Driver;
import org.example.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    DriverService driverService;


    @Autowired
    public DriverController (DriverService driverService){
        this.driverService=driverService;
    }


    @PostMapping("/add")
    public String saveDriver(@RequestBody DriverDTO driverDTO){
        return driverService.saveDriver(driverDTO);
    }

    @GetMapping("/getAllDriver")
    public List<Driver>getAllDrivers(){
        return driverService.getAllDrivers();
    }
}
