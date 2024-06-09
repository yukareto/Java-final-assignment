package integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.yureto.supergt.SupergtApplication;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = SupergtApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SuperGtRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void ドライバー情報が全て取得されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/superGt"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = """
            [
                {
                    "id": 1,
                    "driver": "山本尚貴",
                    "affiliatedTeam": "RAYBRIG NSX-GT",
                    "carNumber": "100"
                },
                {
                    "id": 2,
                    "driver": "大湯都史樹",
                    "affiliatedTeam": "ARTA MUGEN NSX-GT",
                    "carNumber": "8"
                },
                {
                    "id": 3,
                    "driver": "立川裕路",
                    "affiliatedTeam": "ZENT CERUMO GR Supra",
                    "carNumber": "38"
                },
                {
                    "id": 4,
                    "driver": "蒲生尚弥",
                    "affiliatedTeam": "LEON PYRAMID AMG",
                    "carNumber": "65"
                },
                {
                    "id": 5,
                    "driver": "井口卓人",
                    "affiliatedTeam": "SUBARU BRZ R&D SPORT",
                    "carNumber": "61"
                },
                {
                    "id": 6,
                    "driver": "荒聖治",
                    "affiliatedTeam": "Studie BMW M4",
                    "carNumber": "7"
                },
                {
                    "id": 7,
                    "driver": "谷口信輝",
                    "affiliatedTeam": "GOODSMILE RACING & TeamUKYO",
                    "carNumber": "4"
                }
            ]
        """;

        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 新しいドライバーが追加されること() throws Exception {
        String newDriver = """
            {
                "driver": "藤井誠暢",
                "affiliatedTeam": "D'station Vantage GT3",
                "carNumber": "777"
            }
        """;

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/superGt")
                        .contentType("application/json")
                        .content(newDriver))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = """
            {
                "driver": "藤井誠暢",
                "affiliatedTeam": "D'station Vantage GT3",
                "carNumber": "777"
            }
        """;

        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.LENIENT);
    }
}
