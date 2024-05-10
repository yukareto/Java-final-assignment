package com.yureto.supergt.entity;

import java.util.Objects;

public class SuperGt {
    public Integer id;
    public String driver;
    public String affiliatedTeam;
    public String carNumber;

    public SuperGt(Integer id, String driver, String affiliatedTeam, String carNumber) {
        this.id = id;
        this.driver = driver;
        this.affiliatedTeam = affiliatedTeam;
        this.carNumber = carNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getAffiliatedTeam() {
        return affiliatedTeam;
    }

    public void setAffiliatedTeam(String affiliatedTeam) {
        this.affiliatedTeam = affiliatedTeam;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperGt superGt = (SuperGt) o;
        return Objects.equals(id, superGt.id) && Objects.equals(driver, superGt.driver) && Objects.equals(affiliatedTeam, superGt.affiliatedTeam) && Objects.equals(carNumber, superGt.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driver, affiliatedTeam, carNumber);
    }
}
