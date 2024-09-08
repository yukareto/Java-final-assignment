# CRUD処理をすべて備えてREST APIを作成

##

## 概要
SUPER GTのドライバー情報を提供するAPIを作成しました。

##

## APIの内容
- Read処理
    - 全件取得の内容
    - ID指定取得の内容
    - 存在しないID指定取得の内容(例外処理)
    - ドライバー名指定取得の内容
    - 存在しないドライバー名指定取得の内容(例外処理)

- Create処理
    - ドライバー情報の登録
    - 重複登録の内容(例外処理)

- Update処理
    - ドライバー情報の更新
    - 存在しないID指定更新の内容(例外処理)

- Delete処理
    - ドライバー情報の削除
    - 存在しないID指定削除の内容(例外処理)

##

## テスト実装内容
- findAllメソッドの単体テストと結合テスト
    - DBUnit(Database Rider)の実装
    - 結合テストでドライバー情報の全件取得

- findByIdメソッドのDB単体テストと結合テスト
    - ID指定でドライバー情報を取得
    - 存在しないID指定でドライバー情報を取得

- insertメソッドのDB単体テストと結合テスト
    - ドライバー情報の登録
    - 重複登録の内容

- updateメソッドのDB単体テストと結合テスト
    - ドライバー情報の更新
    - 存在しないID指定でドライバー情報を更新

- deleteメソッドのDB単体テストと結合テスト
    - ドライバー情報の削除
    - 存在しないID指定でドライバー情報を削除

## 

## データベース作成時の内容

|**id**|**driver**|**affiliation_team**|**car_number**|
|:---:|:---:|:---:|:---:|
|1|千代勝正|Nitrra MOTUL Z|23|
|2|大湯都史樹|ARTA MUGEN NSX-GT|8|
|3|立川祐路|ZENT CERUMO GR Supra|38|
|4|蒲生尚弥|LEON PYRAMID AMG|65|
|5|井口卓人|SUBARU BRZ R&D SPORT|61|
|6|荒聖治|Studie BMW M4|7|

***

## Read処理の実装結果

<details>
<summary> 全件取得の内容 </summary>

- 全件取得
    - curlコマンド
    ```
    curl --location 'http://localhost:8080/superGt'
    ```
    - 実行結果
      ![GETでの全件取得.1.png](../../%E6%9C%80%E7%B5%82%E8%AA%B2%E9%A1%8C%28README%E7%94%BB%E5%83%8F%29/GET%E3%81%A7%E3%81%AE%E5%85%A8%E4%BB%B6%E5%8F%96%E5%BE%97.1.png)
      ![GETでの全件取得.2.png](../../%E6%9C%80%E7%B5%82%E8%AA%B2%E9%A1%8C%28README%E7%94%BB%E5%83%8F%29/GET%E3%81%A7%E3%81%AE%E5%85%A8%E4%BB%B6%E5%8F%96%E5%BE%97.2.png)

</details>

## 

<details>
<summary> ID指定取得の内容(パスパラメータの実装結果)と例外処理 </summary>

- ID指定取得
    - curlコマンド
    ```
    curl --location 'http://localhost:8080/superGt/3'
    ```
    - 実行結果
      ![GETでのID指定取得.png](../../%E6%9C%80%E7%B5%82%E8%AA%B2%E9%A1%8C%28README%E7%94%BB%E5%83%8F%29/GET%E3%81%A7%E3%81%AEID%E6%8C%87%E5%AE%9A%E5%8F%96%E5%BE%97.png)

##

- 存在しないID指定取得(例外処理)
    - curlコマンド
    ```
    curl --location 'http://localhost:8080/superGt/10'
    ```
    - 実行結果
      ![GETでのIDが存在しない.png](../../%E6%9C%80%E7%B5%82%E8%AA%B2%E9%A1%8C%28README%E7%94%BB%E5%83%8F%29/GET%E3%81%A7%E3%81%AEID%E3%81%8C%E5%AD%98%E5%9C%A8%E3%81%97%E3%81%AA%E3%81%84.png)

</details>

##
