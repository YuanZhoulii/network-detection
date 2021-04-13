package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 9:10
 */
@Data
@ToString
@XmlRootElement(name = "ports")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ports {
    @XmlElement(name = "port")
    private List<Port> portList;
}
