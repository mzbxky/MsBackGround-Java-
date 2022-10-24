package com.ruoyi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//媒体列表结果映射vo
public class MediaManagementVo {
    //媒体id
    @Excel(name="媒体id")
    private String id;
    //媒体名称
    @Excel(name="媒体名称")
    private String name;
    //包名
    @Excel(name="包名")
    private String pkg;
    //别名
    @Excel(name="别名")
    private String exp;
    //修改时间
    @Excel(name="修改时间")
    private String utime;
    //媒体状态(0审核中  1已上线   2已下线)
    @Excel(name="媒体状态")
    private Integer state;
    //c媒体
    @Excel(name="c媒体")
    private String cid;
    //t媒体
    @Excel(name="t媒体")
    private String tid;
    //所属用户
    @Excel(name="所属用户")
    private String userName;
    //版本
    @Excel(name="版本号")
    private String version;
    //所属用户id
    private Long userId;
}
