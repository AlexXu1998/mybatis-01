package com.qf.popj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {//员工类
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer gid;

}
