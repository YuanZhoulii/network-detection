package io.renren.modules.job.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blaze.network.common.constant.DetectConstant;
import com.blaze.network.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/4/2 - 18:23
 */
@Slf4j
@Component("nmapTask")
public class NmapTask implements ITask{
    @Override
    public void run(String params) {
        Process process = null;
//        StringBuffer stringBuffer = new StringBuffer();
        try {
            JSONObject jsonObject = JSON.parseObject(params);
            //扫描类型：ping或all
            String type = (String) jsonObject.get("type");
            //扫描主机地址
            String host= (String) jsonObject.get("host");
            log.debug(type,host);
            //生成Nmap执行参数
            String execParams=getExecParams(type,host);
            System.out.println("execParams = " + execParams);
            //执行程序
            process = Runtime.getRuntime().exec(execParams);
            //读取程序控制台执行结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
            String line = null;
            while((line = reader.readLine()) != null){
//                stringBuffer.append(line + "\n");
                log.debug(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.debug(stringBuffer.toString());
    }
    private static String getExecParams(String type, String hosts) {
        //要输出的文件名：扫描IP+当前时间+XML扩展名
        String fileName= hosts +"-"+DateUtils.format(new Date(),DateUtils.CH_DATE_TIME_PATTERN)+DetectConstant.XML_EXTENSION;
        //建立不同的文件夹存储当天的文件
        String today=DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        //要保存文件的路径：总路径+今天日期+文件名
        String filePath=DetectConstant.XML_SAVE_PATH+ today+"\\"+fileName;
        //如果父目录不存在先创建
        File file=new File(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //指定保存参数
        String fileParam="-oX "+filePath;

        if(type.equals("ping")){
            type = DetectConstant.PING_SCAN_PARAMS;
        }else {
            type =DetectConstant.ALL_SCAN_PARAMS;
        }
        String execParams=String.join(" ",new String[]{DetectConstant.NMAP_PATH, type,fileParam, hosts});
        return execParams;
    }

    public static void main(String[] args) {
        System.out.println(getExecParams("host", "localhost"));
    }
}
