package com.yureto.supergt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuperGtController {

    private final SuperGtMapper superGtMapper;

    public SuperGtController(SuperGtMapper superGtMapper) {
        this.superGtMapper = superGtMapper;
    }

    @GetMapping("/superGt")
    public List<SuperGt> findAll() {
        return superGtMapper.findAll();
    }

    @GetMapping("/superGt/{id}")
    public SuperGt findById(@PathVariable("id") int id) {
        return superGtMapper.findById(id);
    }

    @GetMapping("/superGt/name")
    public List<SuperGt> findByDriver(@RequestParam("driver") String driver) {
        return superGtMapper.findByDriver(driver);
    }
}
