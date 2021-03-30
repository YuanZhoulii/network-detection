package com.blaze.network.visual.controller;

import com.blaze.network.common.utils.R;
import com.blaze.network.visual.common.constant.VisualConstant;
import com.blaze.network.visual.service.EchartsService;
import com.blaze.network.visual.vo.bar.BarItemVo;
import com.blaze.network.visual.vo.bar.BarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yuanzhouli
 * @date 2021/3/24 - 0:56
 */
@RequestMapping("visual/echarts")
@RestController
public class EChartsContoller {
    @Autowired
    EchartsService echartsService;

    /**
     *
     * @return
     * [
     * {name:"省份1",value:99},
     * {name:"省份2",value:99},
     * ...
     * {name:"省份10",value:99}
     * ]
     */
    @GetMapping("/bar/top10")
    public R top10(){
        List<BarItemVo> barItemVoList =new ArrayList<>();
        for(int i=0;i<10;i++){
            BarItemVo barItemVo = new BarItemVo();
            //随机取省份数组中的一个省份
            barItemVo.setName(VisualConstant.PROVINCES[(int) (Math.random()* VisualConstant.PROVINCES.length)]);
            //生成100-999之间的整数
            barItemVo.setValue((int) (100+Math.random()*900));
            barItemVoList.add(barItemVo);
        }

        return R.ok().setData(barItemVoList);
    }
    /**
     *
     * @return
     * [
     * {name:"地级市1",value:99},
     * {name:"地级市2",value:99},
     * ...
     * {name:"地级市10",value:99}
     * ]
     */
    @RequestMapping("/bar/top1")
    public R top1(){
        BarVo barVo = new BarVo();

        return R.ok();
    }


    @RequestMapping("/trend")
    public R trend(){
        return R.ok().setData(echartsService.getTrendData());
    }
    @RequestMapping("/map")
    public R map(){
        return R.ok().setData(echartsService.getMapData());
    }

    @RequestMapping("/rank")
    public R rank(){
        return R.ok().setData(echartsService.getRankData());
    }

    @RequestMapping("/hot")
    public R hot(){
        return R.ok().setData(echartsService.getHotData());
    }

}
