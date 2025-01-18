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

@Mapper
public interface SuperGtMapper {

    // 全てのレコードを取得する
    @Select("SELECT * FROM super_gt")
    List<SuperGt> findAll();

    // ドライバー名で部分一致検索を行う
    @Select("SELECT * FROM super_gt WHERE driver LIKE CONCAT('%', #{driver}, '%')")
    List<SuperGt> findByDriver(String driver);

    // IDを指定してレコードを取得する
    @Select("SELECT * FROM super_gt WHERE id = #{id}")
    Optional<SuperGt> findById(int id);

    // ドライバー名、所属チーム、カーナンバーのいずれかで検索を行う
    @Select("SELECT * FROM super_gt WHERE driver = #{driver} OR affiliated_team = #{affiliatedTeam} OR car_number = #{carNumber}")
    Optional<SuperGt> findByDriverOrAffiliatedTeamOrCarNumber(String driver, String affiliatedTeam, String carNumber);

    // 新しいレコードを挿入する
    @Insert("INSERT INTO super_gt (driver, affiliated_team, car_number) VALUES (#{driver}, #{affiliatedTeam}, #{carNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SuperGt superGt);

    // レコードを更新する
    @Update("UPDATE super_gt SET driver = #{driver}, affiliated_team = #{affiliatedTeam}, car_number = #{carNumber} WHERE id = #{id}")
    void update(SuperGt superGt);

    // IDを指定してレコードを削除する
    @Delete("DELETE FROM super_gt WHERE id = #{id}")
    void delete(Integer id);
}
