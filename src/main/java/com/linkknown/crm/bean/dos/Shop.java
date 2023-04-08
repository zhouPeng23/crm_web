package com.linkknown.crm.bean.dos;

import lombok.Data;

@Data
public class Shop {

    private Integer shopId;

    private String shopName;

    private String shopWeizhi;

    private String shopJingyingType;

    private String shopJingyingBrand;

    private Long shopOpeningDate;

    private String shopLeaderName;

    private Integer shopLeaderSex;

    private String shopLeaderPhoneNumber;

    private String createBy;

    private Long createTime;

    private String updateBy;

    private Long updateTime;

}