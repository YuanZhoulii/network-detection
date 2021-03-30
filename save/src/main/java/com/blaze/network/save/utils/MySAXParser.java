package com.blaze.network.save.utils;


import org.springframework.util.ResourceUtils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * @author yuanzhouli
 * @date 2021/3/17 - 17:51
 */
public class MySAXParser {
    public static void parse(String filePath){
        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
        try {
            SAXParser saxParser=saxParserFactory.newSAXParser();

            String path = ResourceUtils.getURL("classpath:").getPath()+filePath;
//            String path = "C:\\Users\\微星\\Desktop\\nmap_output.xml";
            File file = new File(path);
            saxParser.parse(file,new MyDefaultHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        MySAXParser.parse("nmap_output.xml");
    }
}
