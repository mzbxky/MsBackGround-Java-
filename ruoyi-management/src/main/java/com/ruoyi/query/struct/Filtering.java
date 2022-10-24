package com.ruoyi.query.struct;

import lombok.Data;

import java.util.List;

@Data
public class Filtering {
    private String field;
    private List<String> values;
}
