package com.yureto.supergt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("/superGt")
    public ResponseEntity<SuperGtResponse> insert(@RequestBody @Validated SuperGtRequest superGtRequest, UriComponentsBuilder uriComponentsBuilder) {
        SuperGt superGt = superGtService.insert(superGtRequest.getDriver(), superGtRequest.getAffiliated_team(), superGtRequest.getCar_number());
        URI uri = uriComponentsBuilder.path("/superGtList/{id}").buildAndExpand(superGt.getId()).toUri();
        SuperGtResponse message = new SuperGtResponse("new driver created");
        return ResponseEntity.created(uri).body(message);
    }
}
