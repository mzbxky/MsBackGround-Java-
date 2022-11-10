package com.ruoyi.vo;

import lombok.Data;

@Data
public class PlatformVo {
    private String id;
    private Long platform_id;
    private String name;
    private String company;
    private String secret;
    private String alias;
    //标识
    private Integer type;
}
