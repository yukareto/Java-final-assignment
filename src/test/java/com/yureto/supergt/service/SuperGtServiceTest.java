package com.yureto.supergt.service;

import com.yureto.supergt.entity.SuperGt;
import com.yureto.supergt.mapper.SuperGtMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SuperGtServiceTest {

    @InjectMocks
    SuperGtService superGtService;

    @Mock
    SuperGtMapper superGtMapper;

    @Test
    public void ドライバー情報が全て取得されること() {
        List<SuperGt> superGt = List.of(
                new SuperGt(1, "山本尚貴", "RAYBRIG-NSX-GT", "100"),
                new SuperGt(2, "大湯都史樹", "ARTA MUGEN NSX-GT", "8"),
                new SuperGt(3, "立川裕路", "ZENT CERUMO GR Supra", "38"),
                new SuperGt(4, "蒲生尚弥", "LEON PYRAMID AMG", "65"),
                new SuperGt(5, "井口卓人", "SUBARU BRZ R&D SPORT", "61"),
                new SuperGt(6, "荒聖治", "Studie BMW M4", "7"),
                new SuperGt(7, "谷口信輝", "GOODSMILE RACING & TeamUKYO", "4")
        );
        doReturn(superGt).when(superGtMapper).findAll();
        List<SuperGt> actual = superGtService.findAll();
        assertThat(actual).isEqualTo(superGt);
        verify(superGtMapper).findAll();
    }
}
