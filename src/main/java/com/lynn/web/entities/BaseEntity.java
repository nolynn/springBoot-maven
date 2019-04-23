package com.lynn.web.entities;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Date: 2019/4/23 18:20
 * @Auther: lynn
 */
@Data
public class BaseEntity {
    private String id;
    private String createBy;
    private String createByName;
    private Date createDate;
    private String updateBy;
    private String updateByName;
    private Date updateDate;
    private String delFlag;
    private String remarks;
}
