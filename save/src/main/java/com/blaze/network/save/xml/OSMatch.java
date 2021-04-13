package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 19:58
 */
@Data
@ToString
@XmlRootElement(name = "osmatch")
@XmlAccessorType(XmlAccessType.FIELD)
public class OSMatch {
    @XmlAttribute(name = "name")
    private String osMatchName;
    @XmlAttribute(name = "accuracy")
    private Integer accuracy;
    @XmlElement(name = "osclass")
    private OSClass osClass;
}
