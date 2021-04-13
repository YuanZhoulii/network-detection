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
@XmlRootElement(name = "hosts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hosts {
    /**
     * 主机存活总数
     */
    @XmlAttribute(name = "up")
    private Integer hostsUp;
    /**
     * 主机未存活总数
     */
    @XmlAttribute(name = "down")
    private Integer hostsDown;
}
