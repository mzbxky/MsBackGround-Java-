package com.ruoyi.query.reportAPI;

import com.ruoyi.query.Enum.GroupBy;
import com.ruoyi.query.struct.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class HourDataQuery {
    private Long member_id;
    private String date;
    //枚举类型数组
    private List<GroupBy> group_by;
    //枚举类型结构体
    private OrderBy order_by;
    private Integer page;
    private Integer page_size;
}
