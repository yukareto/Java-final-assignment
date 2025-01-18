package com.yureto.supergt.controller.request;

import jakarta.validation.constraints.NotNull;

public class SuperGtRequest {
    // ドライバーの名前（nullであってはならない）
    @NotNull(message = "Driver must not be null")
    private final String driver;

    // 所属するチーム（nullであってはならない）
    @NotNull(message = "Affiliated Team must not be null")
    private final String affiliatedTeam;

    // カーナンバー（nullであってはならない）
    @NotNull(message = "Car Number must not be null")
    private final String carNumber;

    // コンストラクタ : リクエストの初期化を行う
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
