package com.yureto.supergt;

import jakarta.validation.constraints.Min;
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
    public ResponseEntity<List<SuperGt>> findAll(@RequestParam(required = false) String driver) {
        List<SuperGt> result;
        if (driver != null) {
            result = superGtService.findByDriver(driver);
        } else {
            result = superGtService.findAll();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/superGt/{id}")
    public ResponseEntity<SuperGt> findById(@PathVariable("id") @Min(1) Integer id) {
        SuperGt superGt = superGtService.findById(id);
        if (superGt != null) {
            return new ResponseEntity<>(superGt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
