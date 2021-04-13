package com.blaze.network.save.xml;

import com.blaze.network.save.xml.JAXBDateFromater;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:37
 */
@Data
@ToString
@XmlRootElement(name = "finished")
@XmlAccessorType(XmlAccessType.FIELD)
public class Finished {
    @XmlJavaTypeAdapter(JAXBDateFromater.class)
    @XmlAttribute(name = "timestr")
    private Date finishTime;
    @XmlAttribute(name = "summary")
    private String summary;
    @XmlAttribute(name = "elapsed")
    private String elapsed;
    @XmlAttribute(name = "exit")
    private String runStatus;

}
