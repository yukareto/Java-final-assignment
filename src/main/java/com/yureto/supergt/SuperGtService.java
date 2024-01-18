package com.yureto.supergt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperGtService {

    private final SuperGtMapper superGtMapper;

    @Autowired
    public SuperGtService(SuperGtMapper superGtMapper) {
        this.superGtMapper = superGtMapper;
    }

    public List<SuperGt> findAll() {
        return superGtMapper.findAll();
    }

    public List<SuperGt> findByDriver(String driver) {
        return superGtMapper.findByDriver(driver);
    }

    public SuperGt findById(int id) {
        Optional<SuperGt> superGtOptional = superGtMapper.findById(id);
        return superGtOptional.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));
    }

}
