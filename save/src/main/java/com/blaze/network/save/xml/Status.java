package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:36
 */
@Data
@ToString
@XmlRootElement(name = "status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
    @XmlAttribute(name = "state")
    private String statusState;
    @XmlAttribute(name = "reason")
    private String statusReason;
    @XmlAttribute(name = "reason_ttl")
    private Integer statusReasonTtl;
}
