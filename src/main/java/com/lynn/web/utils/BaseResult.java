package com.lynn.web.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/4/12 10:48
 * @Auther: lynn
 */
@Data
@ApiModel(value = "BaseResult", description = "返回值基类")
@Accessors(chain = true)
@NoArgsConstructor
public class BaseResult<T> implements Serializable {
    @ApiModelProperty(value = "是否成功：200 成功, 其他不成功", name = "code", example = "true")
    private int code;

    @ApiModelProperty(value = "code为200时,此字段不为空,代表错误信息", name = "msg", example = "错误信息 : 请重新登陆")
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "请求响应实体类", name = "data")
    private T data = null;

    @ApiModelProperty(value = "总行数", name = "totalCount", example = "100")
    private long totalCount;

    BaseResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
