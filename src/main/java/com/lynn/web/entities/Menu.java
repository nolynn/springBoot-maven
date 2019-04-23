package com.lynn.web.entities;

import lombok.Data;

/**
 * @Description:
 * @Date: 2019/4/23 18:11
 * @Auther: lynn
 */
@Data
public class Menu extends BaseEntity {
    private String parentId;
    private String parentIds;
    private String name;
    private String sort;
    private String href;
    private String isShow;
    private String permission;
    private String tip;
    private String isapp;
}
