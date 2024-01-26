package com.yureto.supergt;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

    @Insert("INSERT INTO superGt (driver, affiliated_team, car_number) VALUES (#{driver}, #{affiliated_team}, #{car_number})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SuperGt superGt);
}
