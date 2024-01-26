package com.yureto.supergt;

public class SuperGtRequest {

    private String driver;

    private String affiliated_team;

    private String car_number;

    public SuperGtRequest(String driver, String affiliated_team, String car_number) {
        this.driver = driver;
        this.affiliated_team = affiliated_team;
        this.car_number = car_number;
    }

    public String getDriver() {
        return driver;
    }

    public String getAffiliated_team() {
        return affiliated_team;
    }

    public String getCar_number() {
        return car_number;
    }
}
