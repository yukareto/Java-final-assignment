package com.yureto.supergt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<SuperGt>> findAll(@RequestParam(required = false, defaultValue = "") String driver) {
        List<SuperGt> result = superGtService.findByDriver(driver);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/superGt/{id}")
    public ResponseEntity<SuperGt> findById(@PathVariable("id") Integer id) {
        SuperGt superGt = superGtService.findById(id);
        return new ResponseEntity<>(superGt, HttpStatus.OK);
    }
}
