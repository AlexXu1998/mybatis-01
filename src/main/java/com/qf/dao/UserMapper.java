package com.qf.dao;

import com.qf.popj.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //查询所有用户
    List<User> findAll();

    //通过姓名查询用户
    User findByUserName(String username); //sql语句片段 + 动态sql：where
    //通过姓名查询用户
    User findByUserNameAndSex_trim(@Param("username") String username,@Param("sex") Character sex); //动态sql：trim + where

    //通过ID修改用户
    int updateUser(User user); //动态sql：set
    //通过ID修改用户
    int updateUser_trim(User user); //动态sql：trim + set

    //通过ID查询用户
    List<User> findAllById(List<Integer> list); //动态sql：foreach

    //通过地址查询用户
    @Select("select * from user where address = #{address}")
    List<User> findByAddress(@Param("address") String address); //mybatis注解操作
}
