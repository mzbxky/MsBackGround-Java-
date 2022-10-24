package com.ruoyi.query;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class PlatformQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String id;
    private Long platform_id;
    private String name;
    private String company;
    private String secret;
    private String alias;
}
