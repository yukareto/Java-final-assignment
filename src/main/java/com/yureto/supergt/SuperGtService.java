package com.yureto.supergt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public SuperGt findById(int id) {
        return superGtMapper.findById(id);
    }

    public List<SuperGt> findByDriver(String driver) {
        return superGtMapper.findByDriver(driver);
    }
}
