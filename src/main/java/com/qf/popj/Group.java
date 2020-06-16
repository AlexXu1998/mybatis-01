package com.qf.popj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {//小组类
    private Integer gid;
    private String gname;
    private List<Employee> emps;
}
