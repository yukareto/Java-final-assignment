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

    public SuperGt insert(SuperGtRequest superGtRequest) {
        String driver = superGtRequest.getDriver();
        String affiliatedTeam = superGtRequest.getAffiliatedTeam();
        String carNumber = superGtRequest.getCarNumber();

        Optional<SuperGt> superGtOptional = superGtMapper.findByDriverOrAffiliatedTeamOrCarNumber(driver, affiliatedTeam, carNumber);
        if (superGtOptional.isPresent()) {
            throw new SuperGtAlreadyExistsException("driver " + driver + " and affiliated_team " + affiliatedTeam + " and car_number " + carNumber + " already exists");
        }

        SuperGt superGt = new SuperGt(null, driver, affiliatedTeam, carNumber);
        superGtMapper.insert(superGt);
        return superGt;
    }

    public SuperGt update(Integer id, SuperGtRequest superGtRequest) {
        Optional<SuperGt> optionalSuperGt = superGtMapper.findById(id);
        SuperGt existingSuperGt = optionalSuperGt.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));

        existingSuperGt.setDriver(superGtRequest.getDriver());
        existingSuperGt.setAffiliatedTeam(superGtRequest.getAffiliatedTeam());
        existingSuperGt.setCarNumber(superGtRequest.getCarNumber());

        superGtMapper.update(existingSuperGt);

        return existingSuperGt;
    }
}
