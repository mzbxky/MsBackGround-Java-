package com.ruoyi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.sql.Struct;

@Data
@ApiModel(description = "返回封装")
public class ResultVo {
    @ApiModelProperty(value = "自定义的状态码，0成功，-1失败",required = true)
    private Integer code;
    @ApiModelProperty(value = "返回提示信息",required = true)
    private String msg;
    @ApiModelProperty(value = "返回的结果",required = true)
    private Object data;
}
