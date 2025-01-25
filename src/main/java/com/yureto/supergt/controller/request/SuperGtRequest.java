package com.yureto.supergt.controller.request;

import jakarta.validation.constraints.NotNull;

/**
 * Super GTに関連する情報をリクエストとして表現するクラスです。
 * このクラスは、ドライバー名、所属チーム、カーナンバーの情報を持ちます。
 */
public class SuperGtRequest {

    /**
     * ドライバーの名前を表します。
     * この値はnullであってはなりません。
     */
    @NotNull(message = "ドライバー名は必須項目です")
    private final String driver;

    /**
     * 所属チームの名前を表します。
     * この値はnullであってはなりません。
     */
    @NotNull(message = "所属チーム名は必須項目です")
    private final String affiliatedTeam;

    /**
     * カーナンバーを表します。
     * この値はnullであってはなりません。
     */
    @NotNull(message = "カーナンバーは必須項目です")
    private final String carNumber;

    /**
     * 新しいSuperGtRequestオブジェクトを作成します。
     *
     * @param driver         ドライバーの名前（必須）
     * @param affiliatedTeam 所属チームの名前（必須）
     * @param carNumber      カーナンバー（必須）
     * @throws NullPointerException 引数がnullの場合にスローされます
     */
    public SuperGtRequest(String driver, String affiliatedTeam, String carNumber) {
        this.driver = driver;
        this.affiliatedTeam = affiliatedTeam;
        this.carNumber = carNumber;
    }

    /**
     * ドライバーの名前を取得します。
     *
     * @return ドライバーの名前
     */
    public String getDriver() {
        return driver;
    }

    /**
     * 所属チームの名前を取得します。
     *
     * @return 所属チームの名前
     */
    public String getAffiliatedTeam() {
        return affiliatedTeam;
    }

    /**
     * カーナンバーを取得します。
     *
     * @return カーナンバー
     */
    public String getCarNumber() {
        return carNumber;
    }
}
