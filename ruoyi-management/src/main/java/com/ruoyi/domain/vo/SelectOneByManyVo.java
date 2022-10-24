package com.ruoyi.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class SelectOneByManyVo {
    private String userId;
    private String userName;
    private List<AdvertisingControlVo> nameList;
}
