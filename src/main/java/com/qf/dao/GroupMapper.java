package com.qf.dao;

import com.qf.popj.Group;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {

    //查询小组信息
    Group selectGroupById(@Param("gid") Integer gid); //及联调用
}
