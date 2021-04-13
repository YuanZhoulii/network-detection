package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 2:43
 */
@Data
@ToString
public class IPInfo {
    private Integer code;
    private InfoData data;
}
@Data
@ToString
class InfoData{
    private String area;
    private String country;
    private String region;
    private String city;
    private String isp;
}