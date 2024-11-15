package org.example.controller;


import org.example.entity.Cab;
import org.example.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @Autowired
    public CabController(CabService cabService) {
        this.cabService = cabService;
    }

    @GetMapping
    public List<Cab> getAllCabs() {
        return cabService.getAllCabs();
    }

    @GetMapping("/{id}")
    public Optional<Cab> getCabById(@PathVariable UUID id) {

        return cabService.getCabById(id);
    }

    @PostMapping("/addCab")
    public Cab saveCab(@RequestBody Cab cab) {
        return cabService.saveCab(cab);
    }

    @DeleteMapping("/{id}")
    public void deleteCab(@PathVariable UUID id) {
        cabService.deleteCab(id);
    }
}
