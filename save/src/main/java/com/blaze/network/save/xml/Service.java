package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.FIELD)
public class Service{
    @XmlAttribute(name = "name")
    private String serviceName;
}