package com.ruoyi.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "天级数据入参（码上数据库）")
public class DayDataListQuery {
    @ApiModelProperty(value = "分页参数",required = true)
    private Integer pageNum;
    @ApiModelProperty(value = "分页参数",required = true)
    private Integer pageSize;
    @ApiModelProperty(value = "媒体名称",required = false)
    private String mediaName;
    @ApiModelProperty(value = "开始时间",required = true)
    private String startDate;
    @ApiModelProperty(value = "结束时间",required = true)
    private String endDate;
    @ApiModelProperty(value = "开发者账号",required = false)
    private Boolean byDate;
    @ApiModelProperty(value = "开发者账号",required = false)
    private Boolean byMediumName;
    @ApiModelProperty(value = "开发者账号",required = false)
    private Boolean byPlacementName;
}
