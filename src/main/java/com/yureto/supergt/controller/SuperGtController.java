package com.yureto.supergt.controller;

import com.yureto.supergt.controller.request.SuperGtRequest;
import com.yureto.supergt.entity.SuperGt;
import com.yureto.supergt.service.SuperGtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/superGt")  // ここでベースパスを指定
public class SuperGtController {
    private final SuperGtService superGtService;

    @Autowired
    public SuperGtController(SuperGtService superGtService) {
        this.superGtService = superGtService;
    }

    @GetMapping
    public ResponseEntity<List<SuperGt>> findAll(@RequestParam(required = false, defaultValue = "") String driver) {
        List<SuperGt> result = superGtService.findByDriver(driver);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperGt> findById(@PathVariable("id") Integer id) {
        SuperGt superGt = superGtService.findById(id);
        return ResponseEntity.ok(superGt);
    }

    @PostMapping
    public ResponseEntity<SuperGt> insert(@RequestBody SuperGtRequest superGtRequest) {
        SuperGt superGt = superGtService.insert(superGtRequest.getDriver(), superGtRequest.getAffiliatedTeam(), superGtRequest.getCarNumber());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(superGt.getId()).toUri();
        return ResponseEntity.created(location).body(superGt);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuperGt> update(@PathVariable("id") Integer id, @RequestBody SuperGtRequest superGtRequest) {
        SuperGt updatedSuperGt = superGtService.update(id, superGtRequest.getDriver(), superGtRequest.getAffiliatedTeam(), superGtRequest.getCarNumber());
        return ResponseEntity.ok(updatedSuperGt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        superGtService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
