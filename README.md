 # CRUD処理をすべて備えたREST APIの作成
 
 ## 概要

 日本最高峰の4輪自動車レースに参戦するチームの情報を
 管理するためのREST APIを作成しました。

 ## APIの内容

 - Read処理
   - 全件取得の実装
   - パスパラメータの実装
   - クエリパラメータの実装
   - パスパラメータの例外処理
   - クエリパラメータの例外処理
 - Create処理
   - データ登録の実装
   - データ登録におけるの例外処理
 - Update処理
   - データ更新の実装
   - データ更新における例外処理
 - Delete処理
   - データ削除の実装
   - データ削除における例外処理

 ## データベース作成時の内容

 | **id** | **driver** |**affiliated_team**|**car_number**|
 |:--:|:--:|:--:|:--:|
 |1|千代勝正|Niterra MOTUL Z|3|
 |2|大湯都史樹|ARTA MUGEN NSX-GT|8|
 |3|立川裕路|ZENT CERUMO GR Supra|38|
 |4|蒲生尚弥|LEON PYRAMID AMG|65|
 |5|井口卓人|SUBARU BRZ R&D SPORT|61|
 |6|荒聖治|Studie BMW M4|7|

 ## Read処理の実装

 以下は処理の実行結果です

 - データベース全件検索の結果
   - curlコマンド
     ```
     curl --location 'http://localhost:8080/superGt' \ | jq
     ```
   - 実行結果
    ![read処理_1.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fread%E5%87%A6%E7%90%86_1.png)
    ![read処理_2.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fread%E5%87%A6%E7%90%86_2.png)


 - パスパラメータを検索
   - curlコマンド
     ```
     curl --location 'http://localhost:8080/superGt/3' \ | jq
     ```
    - id『3』を指定した実行結果
   ![read処理_パスパラメータ.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fread%E5%87%A6%E7%90%86_%E3%83%91%E3%82%B9%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF.png)

 - クエリ文字列でdriverを指定した検索
   - curlコマンド
        ```
        curl --location --request GET 'http://localhost:8080/superGt?driver=大湯都史樹' \ | jq
        ```
    - driver『大湯都史樹』を指定した実行結果
   ![read処理_クエリ文字列.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fread%E5%87%A6%E7%90%86_%E3%82%AF%E3%82%A8%E3%83%AA%E6%96%87%E5%AD%97%E5%88%97.png)

 - クエリ文字列で存在しないdriverを指定した実行結果
    - curlコマンド
       ```
       curl --location --request GET 'http://localhost:8080/superGt?driver=土屋圭市' \ | jq
       ```
   - driver『土屋圭市』を指定した実行結果 
   ![read処理_クエリ文字列_2.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fread%E5%87%A6%E7%90%86_%E3%82%AF%E3%82%A8%E3%83%AA%E6%96%87%E5%AD%97%E5%88%97_2.png)
 - パスパラメータの例外処理
   - curlコマンド
    ```
    curl --location 'http://localhost:8080/superGt/10' \ | jq
    ``` 
    - id『10』を指定した例外処理
    ![read処理_例外処理_パラメター.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fread%E5%87%A6%E7%90%86_%E4%BE%8B%E5%A4%96%E5%87%A6%E7%90%86_%E3%83%91%E3%83%A9%E3%83%A1%E3%82%BF%E3%83%BC.png)

 - クエリ文字列の例外処理
    - curlコマンド
    ```
    curl --location --request GET 'http://localhost:8080/superGt?driver=佐藤琢磨' \ | jq
    ```
    - driver『佐藤琢磨』を指定した例外処理
    ![read処理_例外処理_クエリ文字列.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fread%E5%87%A6%E7%90%86_%E4%BE%8B%E5%A4%96%E5%87%A6%E7%90%86_%E3%82%AF%E3%82%A8%E3%83%AA%E6%96%87%E5%AD%97%E5%88%97.png)

 ## Create処理の実装

 以下は処理の実行結果です

 - データ登録の実行結果
    - curlコマンド
        ```
        curl --location 'http://localhost:8080/superGt' \
        --header 'Content-Type: application/json' \
        --data '{
        "driver" : "谷口信輝",
        "affiliated_team" : "GOODSMILE RACING & TeamUKYO",
        "car_unmber" : "4"
        }'
        ```
    - 実行結果(登録前のSQL)
    ![create処理_1.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fcreate%E5%87%A6%E7%90%86_1.png)
    - 実行結果(Postman)
    ![create処理_3.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fcreate%E5%87%A6%E7%90%86_3.png)
    - 実行結果(登録後のSQL)
    ![create処理_2.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fcreate%E5%87%A6%E7%90%86_2.png)

 - データ登録における例外処理
    - curlコマンド
     ```
      curl -i --location 'http://localhost:8080/superGt' \
      --header 'Content-Type: application/json' \
      --data '{
      "driver" : "谷口信輝",
      "affiliated_team" : "GOODSMILE RACING & TeamUKYO",
      "car_unmber" : "4"
      }'
     ```
    - 実行結果(ターミナル)
    ![create処理_5.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fcreate%E5%87%A6%E7%90%86_5.png)
    - 実行結果(Postman)
    ![create処理_4.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fcreate%E5%87%A6%E7%90%86_4.png)
   
 ## Update処理の実装

 以下は処理の実行結果です
 
 - データ更新の実装結果
    - curlコマンド
    ```
   curl -i --location --request PATCH 'http://localhost:8080/superGt/1' \
   --header 'Content-Type: application/json' \
   --data '{
   "id": 1,
   "driver" : "山本尚貴",
   "affiliatedTeam" : "RAYBRIG-NSX-GT",
   "carNumber" : "100"
   }'
    ```
    - 実行結果(更新前のSQL)
   ![テーブル_2.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2F%E3%83%86%E3%83%BC%E3%83%96%E3%83%AB_2.png)
    - 実行結果(Postman)
   ![スクリーンショット 2024-03-15 16.51.33.png](..%2F..%2FDesktop%2F%E3%82%B9%E3%82%AF%E3%83%AA%E3%83%BC%E3%83%B3%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%202024-03-15%2016.51.33.png)
    - 実行結果(更新後のSQL)
   ![updat処理_1.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fupdat%E5%87%A6%E7%90%86_1.png)
 
    - データ更新における例外処理
     - curlコマンド
     ```
        
     ```
     - Driver must not be nullというメッセージの表示確認結果(ターミナル)
      ![updat処理_10.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fupdat%E5%87%A6%E7%90%86_10.png)
     - Affiliated Team must not be nullというメッセージの表示確認結果(ターミナル)
      ![updat処理_8.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fupdat%E5%87%A6%E7%90%86_8.png)
     - Car Number must not be nullというメッセージの表示確認結果(ターミナル)
      ![updat処理_9.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fupdat%E5%87%A6%E7%90%86_9.png)
   
 ## Delete処理の実装

 以下は処理の実行結果です。

 - データ削除の実行結果
   - curlコマンド
        ```
       curl --location --request DELETE 'http://localhost:8080/superGt/7' \
       --data ''
        ```
    - 実行結果(削除前のSQL)
    ![delete処理_01.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fdelete%E5%87%A6%E7%90%86_01.png)
    - 実行結果(Postman)
    ![delete処理_1.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fdelete%E5%87%A6%E7%90%86_1.png)
    - 実行結果(削除後のSQL)
    ![delete処理_02.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fdelete%E5%87%A6%E7%90%86_02.png)
   
    - データ削除における例外処理
     - curlコマンド
     ```
     curl --location --request DELETE 'http://localhost:8080/superGt/15' \
     --data ''
     ```
    - 実行結果(Postman) 存在しないデータ場合は404を返します。
    ![delete処理_3.png](..%2F..%2FDesktop%2F%E8%AA%B2%E9%A1%8C%E6%8F%90%E5%87%BA%E3%81%AE%E3%82%B9%E3%82%AF%E3%82%B7%E3%83%A7%2FJava%E7%AC%AC10%E5%9B%9E%20CRUD%E5%87%A6%E7%90%86%E5%AE%9F%E8%A3%85%2Fdelete%E5%87%A6%E7%90%86_3.png)
