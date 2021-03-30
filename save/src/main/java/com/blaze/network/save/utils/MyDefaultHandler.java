package com.blaze.network.save.utils;

import com.blaze.network.save.entity.HostEntity;
import com.blaze.network.save.entity.NmapTaskEntity;
import com.blaze.network.save.entity.PortEntity;
import org.springframework.util.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MyDefaultHandler extends DefaultHandler {

    private HostEntity hostEntity;
    private List<HostEntity> hostEntities;
    private NmapTaskEntity nmapTaskEntity;
    private PortEntity portEntity;
    private List<PortEntity> portEntities;
    private String currentTag;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("<==开始xml文档解析==>");
        hostEntities=new ArrayList<>();
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
        if (qName.equals("nmaprun")) {
            nmapTaskEntity = new NmapTaskEntity();
            String args = attributes.getValue("args");
            String startTime = attributes.getValue("startstr");
            Optional.ofNullable(args).ifPresent(a->{
                nmapTaskEntity.setArgs(a);
            });
            Optional.ofNullable(startTime).ifPresent(start->{
                nmapTaskEntity.setStartTime(DateUtils.StringToDate(start));
            });
        }

        if (qName.equals("finished")) {
            String finishTime = attributes.getValue("timestr");
            if (!StringUtils.isEmpty(finishTime)) {
                nmapTaskEntity.setFinishTime(DateUtils.StringToDate(finishTime));
            }
            String summary = attributes.getValue("summary");
            if(!StringUtils.isEmpty(summary)){
                nmapTaskEntity.setSummary(summary);
            }
            String elapsed = attributes.getValue("elapsed");
            if(!StringUtils.isEmpty(elapsed)){
                nmapTaskEntity.setElapsed(elapsed);
            }
            String runStatus = attributes.getValue("exit");
            if(!StringUtils.isEmpty(runStatus)){
                nmapTaskEntity.setRunStatus(runStatus);
            }

        }
        if(qName.equals("hosts")){
            String up = attributes.getValue("up");
            if(!StringUtils.isEmpty(up)){
                nmapTaskEntity.setHostsUp(Integer.parseInt(up));
            }
            String down=attributes.getValue("down");
            if(!StringUtils.isEmpty(down)){
                nmapTaskEntity.setHostsDown(Integer.parseInt(down));
            }
            System.out.println("nmapTaskEntity = " + nmapTaskEntity);
        }

//        System.out.println("===============================================");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

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