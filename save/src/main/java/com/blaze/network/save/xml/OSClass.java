package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 22:23
 */
@Data
@ToString
@XmlRootElement(name = "osclass")
@XmlAccessorType(XmlAccessType.FIELD)
public class OSClass {
    @XmlAttribute(name = "osfamily")
    private String osfamily;
}
