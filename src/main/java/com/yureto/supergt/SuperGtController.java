package com.yureto.supergt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuperGtController {
    private final SuperGtService superGtService;

    public SuperGtController(SuperGtService superGtService) {
        this.superGtService = superGtService;
    }

    @GetMapping("/superGt")
    public List<SuperGt> findAll(@RequestParam(required = false) String driver) {
        if (driver != null) {
            return superGtService.findByDriver(driver);
        } else {
            return superGtService.findAll();
        }
    }

    @GetMapping("/superGt/{id}")
    public SuperGt findById(@PathVariable("id") int id) {
        return superGtService.findById(id);
    }
}