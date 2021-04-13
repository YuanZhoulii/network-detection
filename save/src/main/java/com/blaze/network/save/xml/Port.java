package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@XmlRootElement(name = "port")
@XmlAccessorType(XmlAccessType.FIELD)
public class Port{
    @XmlAttribute(name = "protocol")
    private String protocol;
    @XmlAttribute(name = "portid")
    private Integer num;
    @XmlElement(name = "state")
    private State portState;
    @XmlElement(name = "service")
    private Service service;

}