package com.yureto.supergt;

import jakarta.validation.constraints.NotNull;

public class SuperGtRequest {

    @NotNull(message = "Driver must not be null")
    private String driver;

    @NotNull(message = "Affiliated Team must not be null")
    private String affiliatedTeam;

    @NotNull(message = "Car Number must not be null")
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
