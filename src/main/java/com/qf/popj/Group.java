package com.qf.popj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private Integer gid;
    private String gname;
    private List<Employee> emps;
}
