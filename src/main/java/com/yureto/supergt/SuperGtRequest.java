package com.yureto.supergt;

public class SuperGtRequest {

    private String driver;
    private String affiliatedTeam;
    private String carNumber;

    public SuperGtRequest(String driver, String affiliatedTeam, String carNumber) {
        this.driver = driver;
        this.affiliatedTeam = affiliatedTeam;
        this.carNumber = carNumber;
    }

    public String getDriver() {
        return driver;
    }

    public String getAffiliatedTeam() {
        return affiliatedTeam;
    }

    public String getCarNumber() {
        return carNumber;
    }
}
