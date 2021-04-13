package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:37
 */
@Data
@ToString
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    /**
     * 扫描地址
     * 示例：1.0.1.1
     */
    @XmlAttribute(name = "addr")
    private String address;
    /**
     * 地址类型
     * 示例：ipv4
     */
    @XmlAttribute(name ="addrtype")
    private String addressType;
}
