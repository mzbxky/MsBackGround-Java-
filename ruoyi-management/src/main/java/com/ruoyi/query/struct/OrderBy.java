package com.ruoyi.query.struct;

import com.ruoyi.query.Enum.SortField;
import com.ruoyi.query.Enum.SortType;
import lombok.Data;

@Data
public class OrderBy {
    private SortField sort_field;
    private SortType sort_type;
}
