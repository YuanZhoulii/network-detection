package com.blaze.network.save.common.utils;

import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 5:44
 */
public class XMLUtils {
//        //读取Resource目录下的XML文件
//        Resource resource = new ClassPathResource("nmap_output.xml");

    public static Object xmlToObject(Class<?> clazz, String filePath) {
        //读取指定文件路径的文件转换为字符串
        Object xmlObject=null;
        try{
            String xmlStr = XMLUtils.parseFileToString(filePath);
            //利用JAXBContext将类转为一个实例
            JAXBContext context = JAXBContext.newInstance(clazz);
            //XMl 转为对象的接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Reader reader = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(reader);
            System.out.println("xmlObject = " + xmlObject);
            if (reader != null) {
                reader.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return xmlObject;
    }

    private static String parseFileToString(String filePath)
            throws IOException {
        //读取文件
        Resource fileUrlResource = new FileUrlResource(filePath);
        //利用输入流获取XML文件内容
        InputStreamReader isr =
          new InputStreamReader(fileUrlResource.getInputStream(),
                  "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line = "";
        //将文件内容添加buffer中
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        return buffer.toString();
    }

}
