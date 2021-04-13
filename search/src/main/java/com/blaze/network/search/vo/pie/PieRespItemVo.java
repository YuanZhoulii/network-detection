package com.blaze.network.search.vo.pie;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/3/25 - 21:56
 */
@Data
@ToString
public class PieRespItemVo {
    /**
     * 一级分类
     */
    private String name;
    /**
     * 一级分类的总值
     */
    private int value;
    /**
     * 包含的所有二级分类数据
     */
    private List<PieDataItemVo> children;
}
