package com.lynn.web.entities;

import lombok.Data;

/**
 * @Description:
 * @Date: 2019/4/4 16:42
 * @Auther: lynn
 */
@Data
public class User {
    private String id;
    private String password;
    private String username;
    private String realname;
    private Integer age;
    private Integer sex;
    private String delFlag;
    private int enable;
}
