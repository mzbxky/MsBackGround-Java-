package com.ruoyi.vo;

import lombok.Data;

@Data
public class PageInfoVo {
    private Integer page;
    private Integer page_size;
    private Integer total_number;
    private Integer total_page;
}
