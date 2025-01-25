package com.yureto.supergt.service;

import com.yureto.supergt.entity.SuperGt;
import com.yureto.supergt.exception.SuperGtAlreadyExistsException;
import com.yureto.supergt.exception.SuperGtNotFoundException;
import com.yureto.supergt.mapper.SuperGtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Super GTに関するビジネスロジックを提供するサービスクラス。
 */
@Service
public class SuperGtService {

    private final SuperGtMapper superGtMapper;

    /**
     * コンストラクタ。
     * SuperGtMapperを注入します。
     *
     * @param superGtMapper Super GTデータベース操作用のMapper
     */
    @Autowired
    public SuperGtService(SuperGtMapper superGtMapper) {
        this.superGtMapper = superGtMapper;
    }

    /**
     * 全てのレコードを取得します。
     *
     * @return Super GTエンティティのリスト
     */
    public List<SuperGt> findAll() {
        return superGtMapper.findAll();
    }

    /**
     * ドライバー名でレコードを検索します。
     *
     * @param driver 検索対象のドライバー名
     * @return 部分一致するSuper GTエンティティのリスト
     */
    public List<SuperGt> findByDriver(String driver) {
        return superGtMapper.findByDriver(driver);
    }

    /**
     * IDを指定してレコードを取得します。
     *
     * @param id 検索対象のID
     * @return 指定されたIDに一致するSuper GTエンティティ
     * @throws SuperGtNotFoundException レコードが存在しない場合にスローされます
     */
    public SuperGt findById(int id) {
        Optional<SuperGt> superGtOptional = superGtMapper.findById(id);
        // レコードが存在しない場合は例外をスロー
        return superGtOptional.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));
    }

    /**
     * 新しいレコードを挿入します。
     *
     * @param driver         ドライバー名
     * @param affiliatedTeam 所属チーム名
     * @param carNumber      カーナンバー
     * @return 挿入されたSuper GTエンティティ
     * @throws SuperGtAlreadyExistsException 同じデータが既に存在する場合にスローされます
     */
    public SuperGt insert(String driver, String affiliatedTeam, String carNumber) {
        // 同じドライバー名、所属チーム、カーナンバーが存在するかを確認
        Optional<SuperGt> superGtOptional = superGtMapper.findByDriverOrAffiliatedTeamOrCarNumber(driver, affiliatedTeam, carNumber);
        if (superGtOptional.isPresent()) {
            throw new SuperGtAlreadyExistsException("driver " + driver + " and affiliated_team " + affiliatedTeam + " and car_number " + carNumber + " already exists");
        }

        // 新規エンティティを作成し、挿入
        SuperGt superGt = new SuperGt(null, driver, affiliatedTeam, carNumber);
        superGtMapper.insert(superGt);
        return superGt;
    }

    /**
     * レコードを更新します。
     *
     * @param id             更新対象のID
     * @param driver         新しいドライバー名
     * @param affiliatedTeam 新しい所属チーム名
     * @param carNumber      新しいカーナンバー
     * @return 更新されたSuper GTエンティティ
     * @throws SuperGtNotFoundException 指定されたIDのレコードが存在しない場合にスローされます
     */
    public SuperGt update(Integer id, String driver, String affiliatedTeam, String carNumber) {
        // 指定されたIDのレコードを検索
        Optional<SuperGt> optionalSuperGt = superGtMapper.findById(id);
        SuperGt existingSuperGt = optionalSuperGt.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));

        // 更新する値をセット
        existingSuperGt.setDriver(driver);
        existingSuperGt.setAffiliatedTeam(affiliatedTeam);
        existingSuperGt.setCarNumber(carNumber);

        // 更新処理を実行
        superGtMapper.update(existingSuperGt);

        return existingSuperGt;
    }

    /**
     * レコードを削除します。
     *
     * @param id 削除対象のID
     * @return 削除したレコードのID
     * @throws SuperGtNotFoundException 指定されたIDのレコードが存在しない場合にスローされます
     */
    public Integer delete(Integer id) {
        // 指定されたIDのレコードを検索
        Optional<SuperGt> optionalSuperGt = superGtMapper.findById(id);
        SuperGt existingSuperGt = optionalSuperGt.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));

        // レコードを削除
        superGtMapper.delete(id);
        return id;
    }
}
