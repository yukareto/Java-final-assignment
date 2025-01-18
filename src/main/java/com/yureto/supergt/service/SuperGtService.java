package com.yureto.supergt.service;

import com.yureto.supergt.entity.SuperGt;
import com.yureto.supergt.exception.SuperGtAlreadyExistsException;
import com.yureto.supergt.exception.SuperGtNotFoundException;
import com.yureto.supergt.mapper.SuperGtMapper;
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

    // 全てのレコードを取得するメソッド
    public List<SuperGt> findAll() {
        return superGtMapper.findAll();
    }

    // ドライバー名でレコードを検索するメソッド
    public List<SuperGt> findByDriver(String driver) {
        return superGtMapper.findByDriver(driver);
    }

    // IDを指定してレコードを取得するメソッド
    public SuperGt findById(int id) {
        Optional<SuperGt> superGtOptional = superGtMapper.findById(id);
        // レコードが存在しない場合は例外をスロー
        return superGtOptional.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));
    }

    // 新しいレコードを挿入するメソッド
    public SuperGt insert(String driver, String affiliatedTeam, String carNumber) {
        Optional<SuperGt> superGtOptional = superGtMapper.findByDriverOrAffiliatedTeamOrCarNumber(driver, affiliatedTeam, carNumber);
        if (superGtOptional.isPresent()) {
            throw new SuperGtAlreadyExistsException("driver " + driver + " and affiliated_team " + affiliatedTeam + " and car_number " + carNumber + " already exists");
        }

        // 新規エンティティを作成し、挿入
        SuperGt superGt = new SuperGt(null, driver, affiliatedTeam, carNumber);
        superGtMapper.insert(superGt);
        return superGt;
    }


    // レコードを更新するメソッド
    public SuperGt update(Integer id, String driver, String affiliatedTeam, String carNumber) {
        Optional<SuperGt> optionalSuperGt = superGtMapper.findById(id);
        SuperGt existingSuperGt = optionalSuperGt.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));

        // 更新する値をセット
        existingSuperGt.setDriver(driver);
        existingSuperGt.setAffiliatedTeam(affiliatedTeam);
        existingSuperGt.setCarNumber(carNumber);

        superGtMapper.update(existingSuperGt);

        return existingSuperGt;
    }

    // レコードを削除するメソッド
    public Integer delete(Integer id) {
        Optional<SuperGt> optionalSuperGt = superGtMapper.findById(id);
        SuperGt existingSuperGt = optionalSuperGt.orElseThrow(() -> new SuperGtNotFoundException("SuperGt with id " + id + " not found"));
        superGtMapper.delete(id);
        return id;
    }
}
