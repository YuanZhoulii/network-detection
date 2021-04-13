package com.blaze.network.search.service;


import com.blaze.network.search.vo.MapItemVo;
import com.blaze.network.search.vo.bar.BarItemVo;
import com.blaze.network.search.vo.pie.PieRespItemVo;

import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 4:37
 */
public interface SearchService {
    List<MapItemVo> getMapData();

    List<BarItemVo> getBarData();

    List<PieRespItemVo> getPieData();
}
