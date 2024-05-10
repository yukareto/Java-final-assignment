package com.yureto.supergt.mapper;

import com.yureto.supergt.entity.SuperGt;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SuperGtMapperTest {

    @Autowired
    SuperGtMapper superGtMapper;

    @Test
    @Sql(
            scripts = {"classpath:/sqlannotation/delete-superGt.sql", "classpath:/sqlannotation/insert-superGt.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
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
}
