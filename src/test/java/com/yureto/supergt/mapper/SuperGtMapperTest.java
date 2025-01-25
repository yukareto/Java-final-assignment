package com.yureto.supergt.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.yureto.supergt.entity.SuperGt;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * SuperGtMapperのテストクラス。
 * データベース操作が期待通りに動作するかを検証します。
 */
@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SuperGtMapperTest {

    @Autowired
    SuperGtMapper superGtMapper;

    /**
     * 全てのドライバー情報が正しく取得されることを確認します。
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void ドライバー情報が全て取得されること() {
        // 全レコードを取得
        List<SuperGt> superGt = superGtMapper.findAll();

        // レコードの数と内容を検証
        assertThat(superGt)
                .hasSize(7)
                .contains(
                        new SuperGt(1, "山本尚貴", "RAYBRIG NSX-GT", "100"),
                        new SuperGt(2, "大湯都史樹", "ARTA MUGEN NSX-GT", "8"),
                        new SuperGt(3, "立川裕路", "ZENT CERUMO GR Supra", "38"),
                        new SuperGt(4, "蒲生尚弥", "LEON PYRAMID AMG", "65"),
                        new SuperGt(5, "井口卓人", "SUBARU BRZ R&D SPORT", "61"),
                        new SuperGt(6, "荒聖治", "Studie BMW M4", "7"),
                        new SuperGt(7, "谷口信輝", "GOODSMILE RACING & TeamUKYO", "4")
                );
    }

    /**
     * 指定したIDのドライバー情報が1件取得されることを確認します。
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが1件取得されること() {
        // 指定IDでレコードを取得
        Optional<SuperGt> superGt = superGtMapper.findById(1);

        // 期待されるレコードが取得されていることを検証
        assertThat(superGt)
                .contains(new SuperGt(1, "山本尚貴", "RAYBRIG NSX-GT", "100"));
    }

    /**
     * 指定したIDのドライバー情報が存在しない場合に空の結果が返されることを確認します。
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが存在しない場合は空を返すこと() {
        // 存在しないIDでレコードを取得
        Optional<SuperGt> superGt = superGtMapper.findById(15);

        // 結果が空であることを検証
        assertThat(superGt).isEmpty();
    }

    /**
     * 新しいドライバーが正しく追加されることを確認します。
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 新しいドライバーが追加されること() {
        // 新しいドライバー情報を作成
        SuperGt newDriver = new SuperGt(null, "藤井誠暢", "D'station Vantage GT3", "777");
        superGtMapper.insert(newDriver);

        // 挿入後に自動生成されたIDが設定されていることを確認
        assertThat(newDriver.getId()).isNotNull();

        // 挿入されたレコードを取得し、内容を検証
        Optional<SuperGt> insertedDriver = superGtMapper.findById(newDriver.getId());
        assertThat(insertedDriver)
                .contains(new SuperGt(newDriver.getId(), "藤井誠暢", "D'station Vantage GT3", "777"));
    }

    /**
     * 指定したIDのドライバー情報が正しく更新されることを確認します。
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが更新されること() {
        // 更新対象のドライバー情報を作成
        SuperGt updatedDriver = new SuperGt(1, "福住仁嶺", "ENEOS X PRIME GR Supra", "14");
        updatedDriver.setDriver("福住仁嶺");
        updatedDriver.setAffiliatedTeam("ENEOS X PRIME GR Supra");
        updatedDriver.setCarNumber("14");

        // 更新処理を実行
        superGtMapper.update(updatedDriver);

        // 更新後のデータを取得し、内容を検証
        Optional<SuperGt> superGt = superGtMapper.findById(1);
        assertThat(superGt)
                .contains(new SuperGt(1, "福住仁嶺", "ENEOS X PRIME GR Supra", "14"));
    }

    /**
     * 指定したIDのドライバー情報が正しく削除されることを確認します。
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが削除されること() {
        // 指定IDのドライバーを削除
        superGtMapper.delete(7);

        // 削除されたレコードが存在しないことを検証
        Optional<SuperGt> superGt = superGtMapper.findById(7);
        assertThat(superGt).isEmpty();
    }
}
