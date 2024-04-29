package com.example.k3s2_psk1lab.mybatis.dao;

import com.example.k3s2_psk1lab.mybatis.model.Team;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    int insert(Team record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    Team selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    List<Team> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 29 03:49:47 EEST 2024
     */
    int updateByPrimaryKey(Team record);
}