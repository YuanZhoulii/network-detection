package com.blaze.network.visual.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuanzhouli
 * @date 2021/3/25 - 22:47
 */
@Getter
@AllArgsConstructor
public enum TypeEnum {
    SERVICE("service", "服务", "服务变化趋势"),
    PORT("port", "端口", "端口变化趋势"),
    OPERATOR_SYSTEM("os", "操作系统", "操作系统变化趋势"),

    ;
    private String en;
    private String ch;
    private String trend;

}
