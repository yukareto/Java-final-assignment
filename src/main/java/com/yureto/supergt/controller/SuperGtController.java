package com.yureto.supergt.controller;

import com.yureto.supergt.controller.request.SuperGtRequest;
import com.yureto.supergt.entity.SuperGt;
import com.yureto.supergt.service.SuperGtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Super GTに関するリクエストを処理するコントローラクラス。
 * REST APIエンドポイントを提供します。
 */
@RestController
@RequestMapping("/superGt")  // ここでベースパスを指定
public class SuperGtController {
    private final SuperGtService superGtService;

    /**
     * コンストラクタ。
     * SuperGtServiceを注入します。
     *
     * @param superGtService Super GTに関連するビジネスロジックを処理するサービス
     */
    @Autowired
    public SuperGtController(SuperGtService superGtService) {
        this.superGtService = superGtService;
    }

    /**
     * ドライバー名を指定してSuper GTの情報を取得します。
     *
     * @param driver ドライバー名（省略可能、デフォルトは空文字列）
     * @return ドライバー名に一致するSuper GTエンティティのリスト
     */
    @GetMapping
    public ResponseEntity<List<SuperGt>> findAll(@RequestParam(required = false, defaultValue = "") String driver) {
        // ドライバー名でフィルタリングしてデータを取得
        List<SuperGt> result = superGtService.findByDriver(driver);
        return ResponseEntity.ok(result);
    }

    /**
     * IDを指定してSuper GTの情報を取得します。
     *
     * @param id Super GTのID
     * @return 指定されたIDに対応するSuper GTエンティティ
     */
    @GetMapping("/{id}")
    public ResponseEntity<SuperGt> findById(@PathVariable("id") Integer id) {
        // 指定されたIDに対応するデータを取得
        SuperGt superGt = superGtService.findById(id);
        return ResponseEntity.ok(superGt);
    }

    /**
     * Super GTの新しい情報を登録します。
     *
     * @param superGtRequest 登録するSuper GTの情報
     * @return 作成されたSuper GTエンティティとリソースURI
     */
    @PostMapping
    public ResponseEntity<SuperGt> insert(@RequestBody SuperGtRequest superGtRequest) {
        // リクエストデータを元に新しいレコードを作成
        SuperGt superGt = superGtService.insert(superGtRequest.getDriver(), superGtRequest.getAffiliatedTeam(), superGtRequest.getCarNumber());

        // 作成されたリソースのURIを生成
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(superGt.getId()).toUri();
        return ResponseEntity.created(location).body(superGt);
    }

    /**
     * IDを指定してSuper GTの情報を更新します。
     *
     * @param id             更新対象のID
     * @param superGtRequest 更新する情報
     * @return 更新されたSuper GTエンティティ
     */
    @PatchMapping("/{id}")
    public ResponseEntity<SuperGt> update(@PathVariable("id") Integer id, @RequestBody SuperGtRequest superGtRequest) {
        // 指定されたIDのレコードを更新
        SuperGt updatedSuperGt = superGtService.update(id, superGtRequest.getDriver(), superGtRequest.getAffiliatedTeam(), superGtRequest.getCarNumber());
        return ResponseEntity.ok(updatedSuperGt);
    }

    /**
     * IDを指定してSuper GTの情報を削除します。
     *
     * @param id 削除対象のID
     * @return HTTPステータス204（No Content）
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        // 指定されたIDのレコードを削除
        superGtService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
