package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author yuanzhouli
 * @date 2021/4/10 - 3:42
 */
@Data
@ToString
@XmlRootElement(name = "nmaprun")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetectOsStatus {
    @XmlElement(name="host")
    private Host host;
}
