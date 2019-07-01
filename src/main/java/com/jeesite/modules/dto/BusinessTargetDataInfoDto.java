package com.jeesite.modules.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessTargetDataInfoDto{


    private String id;
    private String itemName;
    private String itemDescription;
    private String dataInfo;
}
