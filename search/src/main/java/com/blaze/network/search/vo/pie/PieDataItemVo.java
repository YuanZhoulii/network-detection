package com.blaze.network.search.vo.pie;

import lombok.Data;
import lombok.ToString;

/**
 * @author yuanzhouli
 * @date 2021/3/25 - 21:59
 */
@Data
@ToString
public class PieDataItemVo {
    /**
     * 二级分类
     */
    private String name;
    /**
     * 每个二级分类的数值
     */
    private int value;

}
