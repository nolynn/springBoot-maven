package com.lynn.web.entities.params;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Date: 2019/4/26 11:23
 * @Auther: lynn
 */
@Data
public class UserSelectVo {
    @NotNull(message = "username不允许为空")
    private String username;
    @Min(value = 0, message = "年龄必须大于0岁")
    private int age;
}
