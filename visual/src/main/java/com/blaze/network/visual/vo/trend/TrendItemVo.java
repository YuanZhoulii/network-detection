package com.blaze.network.visual.vo.trend;

import com.blaze.network.visual.common.constant.VisualConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yuanzhouli
 * @date 2021/3/24 - 17:59
 */
@Getter
@Setter
@ToString
public class TrendItemVo {
    private String name;
    private int[] data;

    public TrendItemVo() {
        data=new int[VisualConstant.YEARS.length];
    }
}
