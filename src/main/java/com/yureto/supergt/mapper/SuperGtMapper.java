package com.yureto.supergt.mapper;

import com.yureto.supergt.entity.SuperGt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SuperGtMapper {

    @Select("SELECT * FROM super_gt")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "affiliatedTeam", column = "affiliated_team"),
            @Result(property = "carNumber", column = "car_number")
    })
    List<SuperGt> findAll();

    @Select("SELECT * FROM super_gt WHERE driver LIKE CONCAT('%', #{driver}, '%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "affiliatedTeam", column = "affiliated_team"),
            @Result(property = "carNumber", column = "car_number")
    })
    List<SuperGt> findByDriver(String driver);

    @Select("SELECT * FROM super_gt WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "affiliatedTeam", column = "affiliated_team"),
            @Result(property = "carNumber", column = "car_number")
    })
    Optional<SuperGt> findById(int id);

    @Select("SELECT * FROM super_gt WHERE driver = #{driver} OR affiliated_team = #{affiliated_team} OR car_number = #{car_number}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "driver", column = "driver"),
            @Result(property = "affiliatedTeam", column = "affiliated_team"),
            @Result(property = "carNumber", column = "car_number")
    })
    Optional<SuperGt> findByDriverOrAffiliatedTeamOrCarNumber(String driver, String affiliated_team, String car_number);

    @Insert("INSERT INTO super_gt (driver, affiliated_team, car_number) VALUES (#{driver}, #{affiliatedTeam}, #{carNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(SuperGt superGt);

    @Update("UPDATE super_gt SET driver = #{driver}, affiliated_team = #{affiliatedTeam}, car_number = #{carNumber} WHERE id = #{id}")
    void update(SuperGt superGt);

    @Delete("DELETE FROM super_gt WHERE id = #{id}")
    void delete(Integer id);
}
