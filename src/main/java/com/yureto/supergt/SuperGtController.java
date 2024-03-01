package com.yureto.supergt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuperGtController {
    private final SuperGtService superGtService;

    @Autowired
    public SuperGtController(SuperGtService superGtService) {
        this.superGtService = superGtService;
    }

    @GetMapping("/superGt")
    public ResponseEntity<List<SuperGt>> findAll(@RequestParam(required = false, defaultValue = "") String driver) {
        List<SuperGt> result = superGtService.findByDriver(driver);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/superGt/{id}")
    public ResponseEntity<SuperGt> findById(@PathVariable("id") Integer id) {
        SuperGt superGt = superGtService.findById(id);
        return ResponseEntity.ok(superGt);
    }

    @PostMapping("/superGt")
    public ResponseEntity<SuperGtResponse> insert(@RequestBody SuperGtRequest superGtRequest) {
        SuperGt superGt = superGtService.insert(superGtRequest.getDriver(), superGtRequest.getAffiliatedTeam(), superGtRequest.getCarNumber());
        SuperGtResponse message = new SuperGtResponse("new driver created");
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/superGt/{id}")
    public ResponseEntity<SuperGtResponse> update(@PathVariable("id") Integer id, @RequestBody SuperGtRequest superGtRequest) {
        SuperGt updatedSuperGt = superGtService.update(id, superGtRequest.getDriver(), superGtRequest.getAffiliatedTeam(), superGtRequest.getCarNumber());
        SuperGtResponse message = new SuperGtResponse("driver updated");
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/superGt/{id}")
    public ResponseEntity<SuperGtResponse> delete(@PathVariable("id") Integer id) {
        Integer deletedId = superGtService.delete(id);
        SuperGtResponse message = new SuperGtResponse("driver deleted");
        return ResponseEntity.ok(message);
    }
}
