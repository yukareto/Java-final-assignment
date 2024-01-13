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

    public SuperGt findById(int id) {
        Optional<SuperGt> superGt = Optional.ofNullable(this.superGtMapper.findById(id));
        return superGt.orElseThrow(() -> new SuperGtNotFoundException("superGt_id : " + id +  " not found"));
    }

    public List<SuperGt> findByDriver(String driver) {
        List<SuperGt> superGtList = this.superGtMapper.findByDriver(driver);

        if (superGtList == null || superGtList.isEmpty()) {
            throw new SuperGtNotFoundException("SuperGt for driver: " + driver + " not found");
        }

        return superGtList;
    }
}
