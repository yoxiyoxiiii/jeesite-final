package com.jeesite.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    /**
     *
     * 员工ID = userID
     */
    private String emp_code;

    /**
     * 员工姓名=username
     */
    private String emp_name;

    /*
    所在的部门ID
     */
    private String officeCode;
}
