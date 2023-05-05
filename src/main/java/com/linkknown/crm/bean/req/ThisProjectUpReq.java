package com.linkknown.crm.bean.req;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class ThisProjectUpReq {

    private Integer shopId;

    private Integer currentProjectId;

    private Integer upperProjectId;


}
