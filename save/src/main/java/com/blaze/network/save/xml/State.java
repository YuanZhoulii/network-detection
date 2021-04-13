package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@XmlRootElement(name = "state")
@XmlAccessorType(XmlAccessType.FIELD)
public class State{
    @XmlAttribute(name = "state")
    private String state;
    @XmlAttribute(name = "reason")
    private String stateReason;
    @XmlAttribute(name = "reason_ttl")
    private Integer stateReasonTtl;
}