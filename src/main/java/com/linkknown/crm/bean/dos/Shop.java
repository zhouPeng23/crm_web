package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * 门店
 * @author zhoupeng
 */
@Data
public class Shop {

    private Integer shopId;

    private String shopName;

    private String shopWeizhi;

    private String shopJingyingType;

    private String shopJingyingBrand;

    private Date shopOpeningDate;

    private String shopLeaderName;

    private Integer shopLeaderSex;

    private String shopLeaderPhoneNumber;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}