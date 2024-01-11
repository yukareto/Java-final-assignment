package com.yureto.supergt;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SuperGtMapper {

    @Select("SELECT * FROM superGt")
    List<SuperGt> findAll();

    @Select("SELECT * FROM superGt WHERE id = #{id}")
    SuperGt findById(int id);
}
