package com.jeesite.modules.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MonitorVo implements Serializable {

    //考核细则
    private String targetContent;
    //考核部门
    private String officeFullName;

    //状态
    private String planStatus;
}
