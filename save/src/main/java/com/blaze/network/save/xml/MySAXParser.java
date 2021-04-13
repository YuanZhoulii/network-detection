package com.blaze.network.save.xml;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * @author yuanzhouli
 * @date 2021/3/17 - 17:51
 */
@Component
public class MySAXParser {
    @Autowired
    HostStatusHandler hostStatusHandler;
    public void parse(String filePath){
        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
        try {
            SAXParser saxParser=saxParserFactory.newSAXParser();

//            String path = ResourceUtils.getURL("classpath:").getPath()+filePath;
//            String path = "C:\\Users\\微星\\Desktop\\nmap_output.xml";
            File file = new File(filePath);
            saxParser.parse(file, hostStatusHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        MySAXParser.parse("nmap_output.xml");
    }
}
