package com.yureto.supergt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        SuperGt superGt = superGtService.insert(superGtRequest.getDriver(), superGtRequest.getAffiliatedTeam(), superGtRequest.getCarNumber());
        URI uri = uriComponentsBuilder.path("/superGtList/{id}").buildAndExpand(superGt.getId()).toUri();
        SuperGtResponse message = new SuperGtResponse("new driver created");
        return ResponseEntity.created(uri).body(message);
    }

    @PatchMapping("/superGt/{id}")
    public ResponseEntity<SuperGtResponse> update(@PathVariable("id") Integer id, @RequestBody @Validated SuperGtRequest superGtRequest) {
        SuperGt superGt = superGtService.findById(id);
        superGt.setDriver(superGtRequest.getDriver());
        superGt.setAffiliatedTeam(superGtRequest.getAffiliatedTeam());
        superGt.setCarNumber(superGtRequest.getCarNumber());
        superGtService.update(id, superGtRequest);
        SuperGtResponse message = new SuperGtResponse("driver updated");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
