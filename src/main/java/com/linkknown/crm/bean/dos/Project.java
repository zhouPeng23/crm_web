package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Project {

    private Integer shopId;

    private Integer projectId;

    private String projectName;

    private BigDecimal projectPrice;


}