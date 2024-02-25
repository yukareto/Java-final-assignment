package com.yureto.supergt;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public SuperGt update(Integer id, SuperGtRequest superGtRequest) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SuperGtRequest>> violations = validator.validate(superGtRequest);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation error: " + violations.iterator().next().getMessage());
        }

        Optional<SuperGt> optionalSuperGt = superGtMapper.findById(id);
        SuperGt existingSuperGt = optionalSuperGt.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));

        existingSuperGt.setDriver(superGtRequest.getDriver());
        existingSuperGt.setAffiliatedTeam(superGtRequest.getAffiliatedTeam());
        existingSuperGt.setCarNumber(superGtRequest.getCarNumber());

        superGtMapper.update(existingSuperGt);

        return existingSuperGt;
    }
}
