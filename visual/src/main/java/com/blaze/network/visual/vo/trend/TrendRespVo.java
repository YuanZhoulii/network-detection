package com.blaze.network.visual.vo.trend;

import lombok.Data;
import lombok.ToString;

/**
 * @author yuanzhouli
 * @date 2021/3/24 - 20:23
 */
@Data
@ToString
public class TrendRespVo {
    private CommonTrendVo common;
    private TrendVo service;
    private TrendVo port;
    private TrendVo os;
    private TrendTypeVo[] type;
}
