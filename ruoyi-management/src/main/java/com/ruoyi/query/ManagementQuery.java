package com.ruoyi.query;

import lombok.Data;

@Data
public class ManagementQuery {
    private Integer pageNum;
    private Integer pageSize;
    //媒体id
    private String id;
    //媒体名称
    private String name;
    //包名
    private String pkg;
    //日期
    private String NowTime;
    //所属用户
    private String userName;
    //别名
    private String exp;
}
