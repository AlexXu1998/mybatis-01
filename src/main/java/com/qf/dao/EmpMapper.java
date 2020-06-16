package com.qf.dao;

import com.qf.popj.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    //根据组号查询员工信息
    List<Employee> selectEmployeeByGid(@Param("gid") Integer gid); //主表查询
}
