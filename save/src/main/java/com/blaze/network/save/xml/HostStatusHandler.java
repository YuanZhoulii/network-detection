package com.blaze.network.save.xml;

import com.blaze.network.save.entity.HostEntity;
import com.blaze.network.save.entity.NmapTaskEntity;
import com.blaze.network.save.service.HostService;
import com.blaze.network.save.service.NmapTaskService;
import com.blaze.network.save.service.TaskHostRelationService;
import com.blaze.network.save.common.utils.DateUtils;
import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Date;

@Component
public class HostStatusHandler extends DefaultHandler {

    @Autowired
    City city;
    @Autowired
    HostService hostService;
    @Autowired
    NmapTaskService nmapTaskService;
    @Autowired
    TaskHostRelationService taskHostRelationService;
    private HostEntity hostEntity;
    private NmapTaskEntity nmapTaskEntity;
    private String currentTag;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("<==开始xml文档解析==>");

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("<==结束xml文档解析==>");
    }

    /**
     * 如果XML文件使用了schema约束<xs:element>
     * uri:schema--targetNameSpace
     * localName--element
     * qName--xs:element
     * 如果不使用
     * uri:null
     * localName:null
     * qName:element
     * <p>
     * Attributes：当前元素的所有属性的集合
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currentTag = qName;
        if(qName.equals("host")){
            hostEntity=new HostEntity();
        }
        else if(qName.equals("status")){
            String state = attributes.getValue("state");
            String reason = attributes.getValue("reason");
            String reason_ttl = attributes.getValue("reason_ttl");
            hostEntity.setStatusState(state);
            hostEntity.setStatusReason(reason);
            hostEntity.setReasonTtl(Integer.parseInt(reason_ttl));
        }else if(qName.equals("address")){
            String addr = attributes.getValue("addr");
            String addrtype = attributes.getValue("addrtype");
            hostEntity.setAddress(addr);
            hostEntity.setAddressType(addrtype);
        }
        else if (qName.equals("nmaprun")) {
            nmapTaskEntity = new NmapTaskEntity();
            String args = attributes.getValue("args");
            String startTime = attributes.getValue("startstr");
//            String scantype = attributes.getValue("scanner");
            nmapTaskEntity.setArgs(args);
            nmapTaskEntity.setStartTime(DateUtils.StringToDate(startTime));
//            nmapTaskEntity.setScanType(scantype);
        } else if (qName.equals("finished")) {
            String finishTime = attributes.getValue("timestr");
            nmapTaskEntity.setFinishTime(DateUtils.StringToDate(finishTime));
            String summary = attributes.getValue("summary");
            nmapTaskEntity.setSummary(summary);
            String elapsed = attributes.getValue("elapsed");
            nmapTaskEntity.setElapsed(elapsed);
            String runStatus = attributes.getValue("exit");
            nmapTaskEntity.setRunStatus(runStatus);
        } else if (qName.equals("hosts")) {
            String up = attributes.getValue("up");
            nmapTaskEntity.setHostsUp(Integer.parseInt(up));
            String down = attributes.getValue("down");
            nmapTaskEntity.setHostsDown(Integer.parseInt(down));
        }else if(qName.equals("scaninfo")){
            String scanType = attributes.getValue("type");
            String protocol = attributes.getValue("protocol");
            nmapTaskEntity.setScanType(scanType);
            nmapTaskEntity.setProtocol(protocol);
        }

//        System.out.println("===============================================");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("scaninfo")){
            nmapTaskService.save(nmapTaskEntity);
        } else if (qName.equals("nmaprun")) {
            System.out.println(nmapTaskEntity);
            nmapTaskService.updateById(nmapTaskEntity);
        }else if(qName.equals("host")){
            if(!("down".equals(hostEntity.getStatusState()))){
                System.out.println(hostEntity);
                hostEntity.setNmapTaskId(nmapTaskEntity.getNmapTaskId());
                hostEntity.setCreateTime(new Date());
                //查询IP地址数据库，保存地址区域信息
                CityInfo cn=null;
                try {
                    cn = city.findInfo(hostEntity.getAddress(), "CN");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(cn!=null){
                    hostEntity.setArea(cn.getRegionName());
                }
                hostService.save(hostEntity);
            }

        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
//        //一对标签中的内容
//        String s = new String(ch, start, length).trim();
//        if (!StringUtils.isEmpty(s)) {
//            System.out.println(s);
//        }
//        System.out.println();
    }


}