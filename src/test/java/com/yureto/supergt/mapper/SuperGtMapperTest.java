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

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SuperGtMapperTest {

    @Autowired
    SuperGtMapper superGtMapper;

    // 全てのドライバー情報が正しく取得されることを確認
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void ドライバー情報が全て取得されること() {
        List<SuperGt> superGt = superGtMapper.findAll();
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

    // 指定したIDのドライバー情報が1件取得されることを確認
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが1件取得されること() {
        Optional<SuperGt> superGt = superGtMapper.findById(1);
        assertThat(superGt)
                .contains(new SuperGt(1, "山本尚貴", "RAYBRIG NSX-GT", "100"));
    }

    // 指定したIDのドライバー情報が存在しない場合に空の結果が返されることを確認
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが存在しない場合は空を返すこと() {
        Optional<SuperGt> superGt = superGtMapper.findById(15);
        assertThat(superGt).isEmpty(); // 存在しないIDを指定
    }

    // 新しいドライバーが正しく追加されることを確認
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 新しいドライバーが追加されること() {
        SuperGt newDriver = new SuperGt(null, "藤井誠暢", "D'station Vantage GT3", "777");
        superGtMapper.insert(newDriver);

        assertThat(newDriver.getId()).isNotNull();

        Optional<SuperGt> insertedDriver = superGtMapper.findById(newDriver.getId());
        assertThat(insertedDriver)
                .contains(new SuperGt(newDriver.getId(), "藤井誠暢", "D'station Vantage GT3", "777"));
    }

    // 指定したIDのドライバー情報が正しく更新されることを確認
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが更新されること() {
        SuperGt updatedDriver = new SuperGt(1, "福住仁嶺", "ENEOS X PRIME GR Supra", "14");
        updatedDriver.setDriver("福住仁嶺");
        updatedDriver.setAffiliatedTeam("ENEOS X PRIME GR Supra");
        updatedDriver.setCarNumber("14");
        superGtMapper.update(updatedDriver);

        // 更新されたデータが正しく取得されることを確認
        Optional<SuperGt> superGt = superGtMapper.findById(1);
        assertThat(superGt)
                .contains(new SuperGt(1, "福住仁嶺", "ENEOS X PRIME GR Supra", "14"));
    }

    // 指定したIDのドライバー情報が正しく削除されることを確認
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが削除されること() {
        superGtMapper.delete(7);
        Optional<SuperGt> superGt = superGtMapper.findById(7);
        assertThat(superGt).isEmpty();
    }
}
