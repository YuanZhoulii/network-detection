package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 21:00
 */
@Data
@ToString
@XmlRootElement(name = "portused")
@XmlAccessorType(XmlAccessType.FIELD)
public class PortUsed {
    @XmlAttribute(name = "state")
    private String portUsedState;
    @XmlAttribute(name = "proto")
    private String portUsedProto;
    @XmlAttribute(name = "portid")
    private String portId;
}
