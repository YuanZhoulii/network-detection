package com.blaze.network.save.xml;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/4/3 - 10:16
 */
@Data
@ToString
@XmlRootElement(name = "nmaprun")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetectAllStatus {
    /**
     * Nmap查询命令
     * 示例:nmap -v -sn -PE -n --min-hostgroup 1024 --min-parallelism 1024 -oX nmap_output.xml 1.0.1.0/24
     */
    @XmlAttribute(name = "args")
    private String args;
    @XmlElement(name = "scaninfo")
    private ScanInfo scanInfo;

    /**
     * 开始时间日期
     * 示例：Wed Mar 17 20:25:25 2021
     */

    @XmlJavaTypeAdapter(JAXBDateFromater.class)
    @XmlAttribute(name = "startstr")
    private Date startTime;
    @XmlElement(name="host")
    private List<Host> hostList;


//    @XmlElement(name="host")
//    private Host host;
    /**
     * 本次Nmap扫描结果统计信息
     */
    @XmlElement(name="runstats")
    private Runstats runstats;
}




