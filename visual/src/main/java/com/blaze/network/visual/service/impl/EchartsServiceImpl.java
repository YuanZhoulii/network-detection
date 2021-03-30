package com.blaze.network.visual.service.impl;

import com.blaze.network.visual.common.constant.TypeEnum;
import com.blaze.network.visual.common.constant.VisualConstant;
import com.blaze.network.visual.service.EchartsService;
import com.blaze.network.visual.vo.HotDataItemVo;
import com.blaze.network.visual.vo.HotRespItemVo;
import com.blaze.network.visual.vo.MapItemVo;
import com.blaze.network.visual.vo.RankItemVo;
import com.blaze.network.visual.vo.trend.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/3/24 - 19:53
 */
@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    CommonTrendVo commonTrendVo;

    /**
     * data:{
     * common:{
     * years:["2014","2015",..."2021"],
     * },
     * service:{
     * title:"服务变化趋势"
     * base:310
     * unit:"万"
     * data:[
     * {name:"mysql",[2014年-2021年的数据]},
     * {name:"http",[2014年-2021年的数据]},
     * ......
     * ]
     * },
     * port:{
     * <p>
     * },
     * os:{
     * <p>
     * }
     * }
     */
    @Override
    public TrendRespVo getTrendData() {
        TrendRespVo trendRespVo = new TrendRespVo();
        trendRespVo.setCommon(commonTrendVo);
        TrendVo service = this.getServiceTrendData();
        trendRespVo.setService(service);
        TrendVo os = this.getOSTrendData();
        trendRespVo.setOs(os);
        TrendVo port = this.getPortTrendData();
        trendRespVo.setPort(port);
        TrendTypeVo[] trendTypeVos = {
                new TrendTypeVo(TypeEnum.SERVICE.getCh(), TypeEnum.SERVICE.getTrend()),
                new TrendTypeVo(TypeEnum.PORT.getCh(), TypeEnum.PORT.getTrend()),
                new TrendTypeVo(TypeEnum.OPERATOR_SYSTEM.getCh(), TypeEnum.OPERATOR_SYSTEM.getTrend())};
        trendRespVo.setType(trendTypeVos);
        return trendRespVo;
    }

    @Override
    public TrendVo getServiceTrendData() {
        //构造服务变化趋势图数据
        TrendVo trendVo = new TrendVo();
        trendVo.setTitle(TypeEnum.SERVICE.getTrend());
        List<TrendItemVo> items = new ArrayList<>();
        for (int i = 0; i < VisualConstant.SERVICE.length; i++) {
            TrendItemVo trendItemVo = new TrendItemVo();
            trendItemVo.setName(VisualConstant.SERVICE[i]);
            int[] values = new int[VisualConstant.YEARS.length];
            for (int j = 0; j < VisualConstant.YEARS.length; j++) {
                values[j] = (int) (1000 + Math.random() * 9000);
            }
            trendItemVo.setData(values);
            items.add(trendItemVo);
        }
        trendVo.setData(items);
        return trendVo;
    }

    @Override
    public TrendVo getPortTrendData() {
        //构造端口变化趋势图数据
        TrendVo trendVo = new TrendVo();
        trendVo.setTitle(TypeEnum.PORT.getTrend());
        List<TrendItemVo> items = new ArrayList<>();
        for (int i = 0; i < VisualConstant.PORT.length; i++) {
            TrendItemVo trendItemVo = new TrendItemVo();
            trendItemVo.setName(VisualConstant.PORT[i]);
            int[] values = new int[VisualConstant.YEARS.length];
            for (int j = 0; j < VisualConstant.YEARS.length; j++) {
                values[j] = (int) (1000 + Math.random() * 9000);
            }
            trendItemVo.setData(values);
            items.add(trendItemVo);
        }
        trendVo.setData(items);
        return trendVo;
    }

    @Override
    public TrendVo getOSTrendData() {
        //构造端口变化趋势图数据
        TrendVo trendVo = new TrendVo();
        trendVo.setTitle(TypeEnum.OPERATOR_SYSTEM.getTrend());
        List<TrendItemVo> items = new ArrayList<>();
        for (int i = 0; i < VisualConstant.OPERATOR_SYSTEM.length; i++) {
            TrendItemVo trendItemVo = new TrendItemVo();
            trendItemVo.setName(VisualConstant.OPERATOR_SYSTEM[i]);
            int[] values = new int[VisualConstant.YEARS.length];
            for (int j = 0; j < VisualConstant.YEARS.length; j++) {
                values[j] = (int) (1000 + Math.random() * 9000);
            }
            trendItemVo.setData(values);
            items.add(trendItemVo);
        }
        trendVo.setData(items);
        return trendVo;
    }

    /**
     * [
     * name:'湖北',value:222
     * name:'湖南',value:222
     * name:'山东',value:222
     * ......
     * ]
     */
    @Override
    public List<MapItemVo> getMapData() {
        List<MapItemVo> list = new ArrayList<>();
        for (String province : VisualConstant.PROVINCES) {
            MapItemVo mapItemVo = new MapItemVo();
            mapItemVo.setName(province);
            mapItemVo.setValue((int) ((Integer.MAX_VALUE / 2) + (Math.random() * (Integer.MAX_VALUE / 2))));
            list.add(mapItemVo);
        }
        return list;
    }

    /**
     * [
     * {name:'湖北',value:222}
     * {name:'湖南',value:222}
     * {name:'山东',value:222}
     * ......
     * ]
     */
    @Override
    public List<RankItemVo> getRankData() {
        List<RankItemVo> list = new ArrayList<>();
        for (String province : VisualConstant.PROVINCES) {
            RankItemVo rankItemVo = new RankItemVo();
            rankItemVo.setName(province);
            rankItemVo.setValue((int) ((Integer.MAX_VALUE / 2) + (Math.random() * (Integer.MAX_VALUE / 2))));
            list.add(rankItemVo);
        }
        return list;
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
     */
    @Override
    public List<HotRespItemVo> getHotData() {
        List<HotRespItemVo> list = new ArrayList<>();
        String[] hotType = {TypeEnum.PORT.getCh(), TypeEnum.OPERATOR_SYSTEM.getCh(), TypeEnum.SERVICE.getCh()};
        for (String type : hotType) {
            HotRespItemVo respItemVo = new HotRespItemVo();
            List<HotDataItemVo> dataItemVos = new ArrayList<>();
            String[] temp=null;
            if(type.equals(TypeEnum.PORT.getCh())){
                temp=VisualConstant.PORT;
                respItemVo.setName(TypeEnum.PORT.getCh());
            }else if(type.equals(TypeEnum.SERVICE.getCh())){
                temp=VisualConstant.SERVICE;
                respItemVo.setName(TypeEnum.SERVICE.getCh());
            }else if(type.equals(TypeEnum.OPERATOR_SYSTEM.getCh())){
                temp=VisualConstant.OPERATOR_SYSTEM;
                respItemVo.setName(TypeEnum.OPERATOR_SYSTEM.getCh());
            }
            int total=0;
            for (String dataItemName : temp) {
                HotDataItemVo dataItemVo = new HotDataItemVo();
                dataItemVo.setName(dataItemName);
                int value = (int) (100 + Math.random() * 900);
                dataItemVo.setValue(value);
                total+=value;
                dataItemVos.add(dataItemVo);
            }

            respItemVo.setValue(total);
            respItemVo.setChildren(dataItemVos);
            list.add(respItemVo);
        }

        return list;
    }
}
