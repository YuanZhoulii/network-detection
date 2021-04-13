package com.blaze.network.search.vo;

/**
 * @author yuanzhouli
 * @date 2021/3/25 - 15:31
 */

public class MapItemVo {
    private String name;
    private Integer value;

    public MapItemVo() {
        this.value=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MapItemVo{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
