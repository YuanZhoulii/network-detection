package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 19:44
 */
@Data
@ToString
@XmlRootElement(name = "scaninfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScanInfo {
    @XmlAttribute(name = "type")
    private String scanType;
    @XmlAttribute(name = "protocol")
    private String protocol;
}
