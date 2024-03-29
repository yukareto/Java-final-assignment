package com.yureto.supergt;

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

    @Select("SELECT * FROM superGt")
    List<SuperGt> findAll();

    @Select("SELECT * FROM superGt WHERE driver LIKE CONCAT('%', #{driver}, '%')")
    List<SuperGt> findByDriver(String driver);

    @Select("SELECT * FROM superGt WHERE id = #{id}")
    Optional<SuperGt> findById(int id);

    @Select("SELECT * FROM superGt WHERE driver = #{driver} OR affiliated_team = #{affiliated_team} OR car_number = #{car_number}")
    Optional<SuperGt> findByDriverOrAffiliatedTeamOrCarNumber(String driver, String affiliated_team, String car_number);

    @Insert("INSERT INTO superGt (driver, affiliated_team, car_number) VALUES (#{driver}, #{affiliated_team}, #{car_number})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SuperGt superGt);

    @Update("UPDATE superGt SET driver = #{driver}, affiliated_team = #{affiliatedTeam}, car_number = #{carNumber} WHERE id = #{id}")
    void update(SuperGt superGt);

    @Delete("DELETE FROM superGt WHERE id = #{id}")
    void delete(Integer id);
}
