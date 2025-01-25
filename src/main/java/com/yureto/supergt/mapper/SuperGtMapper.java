package com.yureto.supergt.mapper;

import com.yureto.supergt.entity.SuperGt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

/**
 * Super GTデータベース操作用のマッパーインターフェース。
 * データベースに対するCRUD操作を定義します。
 */
@Mapper
public interface SuperGtMapper {

    /**
     * 全てのレコードを取得します。
     *
     * @return Super GTエンティティのリスト
     */
    @Select("SELECT * FROM super_gt")
    List<SuperGt> findAll();

    /**
     * ドライバー名で部分一致検索を行います。
     *
     * @param driver 検索対象のドライバー名
     * @return 部分一致するSuper GTエンティティのリスト
     */
    @Select("SELECT * FROM super_gt WHERE driver LIKE CONCAT('%', #{driver}, '%')")
    List<SuperGt> findByDriver(String driver);

    /**
     * IDを指定してレコードを取得します。
     *
     * @param id 検索対象のID
     * @return 検索結果のSuper GTエンティティ（Optional）
     */
    @Select("SELECT * FROM super_gt WHERE id = #{id}")
    Optional<SuperGt> findById(int id);

    /**
     * ドライバー名、所属チーム、カーナンバーのいずれかで検索を行います。
     *
     * @param driver         ドライバー名
     * @param affiliatedTeam 所属チーム名
     * @param carNumber      カーナンバー
     * @return 一致するSuper GTエンティティ（Optional）
     */
    @Select("SELECT * FROM super_gt WHERE driver = #{driver} OR affiliated_team = #{affiliatedTeam} OR car_number = #{carNumber}")
    Optional<SuperGt> findByDriverOrAffiliatedTeamOrCarNumber(String driver, String affiliatedTeam, String carNumber);

    /**
     * 新しいレコードを挿入します。
     *
     * @param superGt 挿入するSuper GTエンティティ
     */
    @Insert("INSERT INTO super_gt (driver, affiliated_team, car_number) VALUES (#{driver}, #{affiliatedTeam}, #{carNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    // IDを自動生成
    void insert(SuperGt superGt);

    /**
     * レコードを更新します。
     *
     * @param superGt 更新対象のSuper GTエンティティ
     */
    @Update("UPDATE super_gt SET driver = #{driver}, affiliated_team = #{affiliatedTeam}, car_number = #{carNumber} WHERE id = #{id}")
    void update(SuperGt superGt);

    /**
     * IDを指定してレコードを削除します。
     *
     * @param id 削除対象のID
     */
    @Delete("DELETE FROM super_gt WHERE id = #{id}")
    void delete(Integer id);
}
