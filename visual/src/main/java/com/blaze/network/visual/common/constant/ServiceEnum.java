package com.blaze.network.visual.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuanzhouli
 * @date 2021/3/25 - 22:49
 */
@Getter
@AllArgsConstructor
public enum ServiceEnum {
    HTTP("http", "283811891"),
    HTTPS("https", "28905392"),
    MS_WBT_SERVER("ms-wbt-server", "5784366"),
    NTP("ntp", "5104103"),
    SSH("ssh", "3072781"),
    UNKNOWN("unknown", "2596076"),
    MYSQL("mysql", "1557694"),
    NAGIOS_NSCA("nagios-nsca", "1548281"),
    UPNP("upnp", "1194850"),

    ;
    private String name;
    private String defaultCount;
}
