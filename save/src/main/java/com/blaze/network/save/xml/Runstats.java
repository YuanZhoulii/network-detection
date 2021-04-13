package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:35
 */
@Data
@ToString
@XmlRootElement(name = "runstats")
@XmlAccessorType(XmlAccessType.FIELD)
public class Runstats {
    @XmlElement(name = "finished")
    private Finished finished;
    @XmlElement(name = "hosts")
    private Hosts hosts;

}
