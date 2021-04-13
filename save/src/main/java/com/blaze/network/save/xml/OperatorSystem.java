package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 19:57
 */
@Data
@ToString
@XmlRootElement(name = "os")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperatorSystem {
    @XmlElement(name = "portused")
    private PortUsed portUsed;
    @XmlElement(name = "osmatch")
    private List<OSMatch> osMatchList;
}
