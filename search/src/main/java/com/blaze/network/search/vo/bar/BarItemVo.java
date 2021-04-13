package com.blaze.network.search.vo.bar;

/**
 * @author yuanzhouli
 * @date 2021/3/24 - 1:31
 */

public class BarItemVo {
    String name;
    Integer value;

    public BarItemVo() {
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
        return "BarItemVo{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
