package com.ruoyi.query.reportAPI;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "天级数据入参")
public class DailyDataQuery {
    @ApiModelProperty(value = "开发者账号",required = true)
    private String member_id;
    @ApiModelProperty(value = "媒体名称",required = false)
    private String medium_name;
    @ApiModelProperty(value = "广告位名称",required = false)
    private String placement_name;
    private enum placement_type{};
    private enum custom_position_scene_level2{};
    private enum price_strategy_type{};
    @ApiModelProperty(value = "开始日期",required = true)
    private Integer start_date;
    @ApiModelProperty(value = "结束日期",required = true)
    private Integer end_date;
    @ApiModelProperty(value = "搜索页码",required = false)
    private Integer page = 1;
    @ApiModelProperty(value = "每页显示条数",required = false)
    private Integer page_size;
    @ApiModelProperty(value = "请求头中的token",required = true)
    private String token;
}
