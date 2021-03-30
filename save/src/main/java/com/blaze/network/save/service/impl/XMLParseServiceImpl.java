package com.blaze.network.save.service.impl;

import com.blaze.network.save.to.NmapRun;
import com.blaze.network.save.utils.XmlBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//@Service
public class XMLParseServiceImpl {
    public NmapRun getNmapRun() throws Exception {
//        Resource resource1=new FileUrlResource("C:\\Users\\微星\\Desktop\\nmap_output.xml");
        //读取Resource目录下的XML文件
        Resource resource = new ClassPathResource("nmap_output.xml");
        //利用输入流获取XML文件内容
        InputStreamReader isr =
                new InputStreamReader(resource.getInputStream(),"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        //XML转为JAVA对象
        NmapRun nmapRun = (NmapRun) XmlBuilder.xmlStrToObject(NmapRun.class, buffer.toString());
        return nmapRun;
    }

    public static void main(String[] args) throws Exception {
        XMLParseServiceImpl xmlParseService = new XMLParseServiceImpl();
        NmapRun nmapRun=xmlParseService.getNmapRun();
        System.out.println("nmapRun = " + nmapRun);
    }
}