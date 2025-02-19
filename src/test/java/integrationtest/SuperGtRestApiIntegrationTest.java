package integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
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

/**
 * Super GT REST APIの統合テストクラス。
 * REST APIエンドポイントが期待通り動作することを検証します。
 */
@SpringBootTest(classes = SupergtApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SuperGtRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * 全てのドライバー情報が正しく取得されることを確認します。
     *
     * @throws Exception テスト中に予期しないエラーが発生した場合
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void ドライバー情報が全て取得されること() throws Exception {
        // GETリクエストを送信し、レスポンスを取得
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/superGt"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        // 期待されるレスポンスデータ
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

        // JSONデータの検証
        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.STRICT);
    }

    /**
     * 指定したドライバーIDが1件取得されることを確認します。
     *
     * @throws Exception テスト中に予期しないエラーが発生した場合
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが1件取得されること() throws Exception {
        // GETリクエストを送信し、レスポンスを取得
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/superGt/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        // 期待されるレスポンスデータ
        String expectedResponse = """
                    {
                        "id": 1,
                        "driver": "山本尚貴",
                        "affiliatedTeam": "RAYBRIG NSX-GT",
                        "carNumber": "100"
                    }
                """;

        // JSONデータの検証
        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.STRICT);
    }

    /**
     * 存在しないドライバーIDを指定した場合、404ステータスを返すことを確認します。
     *
     * @throws Exception テスト中に予期しないエラーが発生した場合
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが存在しない場合は404を返すこと() throws Exception {
        // GETリクエストで存在しないIDを指定
        mockMvc.perform(MockMvcRequestBuilders.get("/superGt/15"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * 新しいドライバーが正しく追加されることを確認します。
     *
     * @throws Exception テスト中に予期しないエラーが発生した場合
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 新しいドライバーが追加されること() throws Exception {
        // POSTリクエストで送信する新しいドライバー情報
        String newDriver = """
                    {
                        "driver": "藤井誠暢",
                        "affiliatedTeam": "D'station Vantage GT3",
                        "carNumber": "777"
                    }
                """;

        // POSTリクエストを送信し、レスポンスを取得
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/superGt")
                        .contentType("application/json")
                        .content(newDriver))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        // 期待されるレスポンスデータ
        String expectedResponse = """
                    {
                        "driver": "藤井誠暢",
                        "affiliatedTeam": "D'station Vantage GT3",
                        "carNumber": "777"
                    }
                """;

        // JSONデータの検証
        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.LENIENT);
    }

    /**
     * 指定したIDのドライバー情報が正しく更新されることを確認します。
     *
     * @throws Exception テスト中に予期しないエラーが発生した場合
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @Transactional
    void 指定したドライバーidが更新されること() throws Exception {
        // PATCHリクエストで送信する更新情報
        String updatedDriver = """
                    {
                        "driver": "福住仁嶺",
                        "affiliatedTeam": "ENEOS X PRIME GR Supra",
                        "carNumber": "14"
                    }
                """;

        // PATCHリクエストを送信し、レスポンスを取得
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/superGt/1")
                        .contentType("application/json")
                        .content(updatedDriver))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        // 期待されるレスポンスデータ
        String expectedResponse = """
                    {
                        "id": 1,
                        "driver": "福住仁嶺",
                        "affiliatedTeam": "ENEOS X PRIME GR Supra",
                        "carNumber": "14"
                    }
                """;

        // JSONデータの検証
        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.LENIENT);
    }

    /**
     * 指定したIDのドライバーが正しく削除されることを確認します。
     *
     * @throws Exception テスト中に予期しないエラーが発生した場合
     */
    @Test
    @DataSet(value = "datasets/super_gt.yml")
    @ExpectedDataSet(value = "datasets/super_gt_after_deletion.yml")
    @Transactional
    void 指定したドライバーidが削除されること() throws Exception {
        // DELETEリクエストを送信
        mockMvc.perform(MockMvcRequestBuilders.delete("/superGt/7"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
