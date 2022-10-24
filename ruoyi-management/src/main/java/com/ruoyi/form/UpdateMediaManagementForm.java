package com.ruoyi.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UpdateMediaManagementForm {
    //媒体id
    private String id;
    //媒体名称
    private String name;
    //包名
    private String pck;
    //别名
    private String exp;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String utime;
    //媒体状态(0审核中  1已上线   2已下线)
    private Integer state;
    //c媒体
    private String cid;
    //t媒体
    private String tid;
    //所属用户
    private String userId;
    //版本
    private String version;
}
