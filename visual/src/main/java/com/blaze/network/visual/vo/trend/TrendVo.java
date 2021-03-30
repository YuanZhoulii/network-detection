package com.blaze.network.visual.vo.trend;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/3/24 - 17:56
 */
@Data
@ToString
public class TrendVo {
    //图表标题
    private String title;
    //单位
    private String unit;
    private List<TrendItemVo> data;
}
