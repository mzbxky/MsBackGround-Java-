package com.ruoyi.form;

import lombok.Data;

@Data
public class UpdatePlatformForm {
    private String id;
    private Long platform_id;
    private String name;
    private String company;
    private String secret;
    private String alias;
}
