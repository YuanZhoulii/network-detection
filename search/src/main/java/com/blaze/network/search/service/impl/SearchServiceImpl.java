package com.blaze.network.search.service.impl;

import com.blaze.network.search.common.constant.TypeEnum;
import com.blaze.network.search.dao.HostDao;
import com.blaze.network.search.dao.PortDao;
import com.blaze.network.search.service.SearchService;
import com.blaze.network.search.vo.MapItemVo;
import com.blaze.network.search.vo.bar.BarItemVo;
import com.blaze.network.search.vo.pie.PieDataItemVo;
import com.blaze.network.search.vo.pie.PieRespItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 4:38
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    HostDao hostDao;
    @Autowired
    PortDao portDao;
    @Override
    public List<MapItemVo> getMapData() {
        List<MapItemVo> mapItemVos = hostDao.QueryForMapData();
        return mapItemVos;
    }

    @Override
    public List<BarItemVo> getBarData() {
        List<BarItemVo> barItemVos = hostDao.QueryForBarData();
        return barItemVos;
    }

    /**
     * [
     *  {
     *      name:'端口',
     *      value:100,
     *      children:
     *      [
     *          {name:'80',value:50},
     *          {name:'88',value:50}
     *      ]
     *  },
     *  {
     *     name:'操作系统',
     *     value:100,
     *     children:
     *     [
     *         {name:'windows',value:50},
     *         {name:'linux',value:50}
     *     ]
     *  },
     *  {
     *    name:'服务',
     *    value:100,
     *    children:
     *    [
     *        {name:'mysql',value:50},
     *        {name:'redis',value:50}
     *    ]
     *  },
     * ]
     *
     *
     * SELECT
     * 	os,
     * 	COUNT( os ) as sum
     * FROM
     * 	save_host
     * WHERE
     * 	os IS NOT NULL and os != ""
     * GROUP BY
     * 	( os )
     * ORDER BY
     * 	COUNT( os ) DESC
     * 	LIMIT 8;
     *
     */
    @Override
    public List<PieRespItemVo> getPieData() {
        int total=0;
        List<PieRespItemVo> list = new ArrayList<>();
        PieRespItemVo serviceItem=new PieRespItemVo();
        serviceItem.setName(TypeEnum.SERVICE.getCh());
        List<PieDataItemVo> serviceDataItem = portDao.queryForServiceData();
        serviceItem.setChildren(serviceDataItem);
        for (PieDataItemVo vo : serviceDataItem) {
            int value=vo.getValue();
            total+=value;
        }
        serviceItem.setValue(total);


        total=0;
        PieRespItemVo portItem=new PieRespItemVo();
        List<PieDataItemVo> portData = portDao.queryForPortData();
        portItem.setChildren(portData);
        for (PieDataItemVo portDatum : portData) {
            int value=portDatum.getValue();
            total+=value;
        }
        portItem.setValue(total);
        portItem.setName(TypeEnum.PORT.getCh());


        PieRespItemVo osItem=new PieRespItemVo();
        osItem.setName(TypeEnum.OPERATOR_SYSTEM.getCh());
        List<PieDataItemVo> osDataItem = hostDao.QueryForOSData();
        osItem.setChildren(osDataItem);
        total=0;
        for (PieDataItemVo pieDataItemVo : osDataItem) {
            int value = pieDataItemVo.getValue();
            total+=value;
        }
        osItem.setValue(total);

        list.add(serviceItem);
        list.add(portItem);
        list.add(osItem);

        return list;
    }


}
