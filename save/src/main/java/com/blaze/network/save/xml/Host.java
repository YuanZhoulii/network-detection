package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:36
 */
@Data
@ToString
@XmlRootElement(name = "host")
@XmlAccessorType(XmlAccessType.FIELD)
public class Host {
    @XmlElement(name = "status")
    private Status status;
    @XmlElement(name = "address")
    private Address address;
    @XmlElement(name = "ports")
    private Ports ports;
    @XmlElement(name = "os")
    private OperatorSystem operatorSystem;
}
