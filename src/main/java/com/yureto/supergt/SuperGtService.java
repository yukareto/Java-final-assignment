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

    public SuperGt insert(String driver, String affiliated_team, String car_number) {
        Optional<SuperGt> superGtOptional = superGtMapper.findByDriverOrAffiliatedTeamOrCarNumber(driver, affiliated_team, car_number);
        if (superGtOptional.isPresent()) {
            throw new SuperGtAlreadyExistsException("driver " + driver + " and affiliated_team " + affiliated_team + " and car_number " + car_number + " already exists");

        }
        SuperGt superGt = new SuperGt(null, driver, affiliated_team, car_number);
        superGtMapper.insert(superGt);
        return superGt;
    }
}
