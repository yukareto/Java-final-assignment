package com.yureto.supergt;

public class SuperGt {
    public int id;

    public String driver;

    public String affiliated_team;

    public String car_number;

    public SuperGt(int id, String driver, String affiliated_team, String car_number) {
        this.id = id;
        this.driver = driver;
        this.affiliated_team = affiliated_team;
        this.car_number = car_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getAffiliated_team() {
        return affiliated_team;
    }

    public void setAffiliated_team(String affiliated_team) {
        this.affiliated_team = affiliated_team;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }
}
