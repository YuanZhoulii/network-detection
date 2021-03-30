package com.blaze.network.visual.service;

import com.blaze.network.visual.vo.HotRespItemVo;
import com.blaze.network.visual.vo.MapItemVo;
import com.blaze.network.visual.vo.RankItemVo;
import com.blaze.network.visual.vo.trend.TrendRespVo;
import com.blaze.network.visual.vo.trend.TrendVo;

import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/3/24 - 19:52
 */
public interface EchartsService {
    TrendVo getServiceTrendData();
    TrendVo getPortTrendData();
    TrendVo getOSTrendData();
    TrendRespVo getTrendData();

    List<MapItemVo> getMapData();

    List<RankItemVo> getRankData();

    List<HotRespItemVo> getHotData();
}
