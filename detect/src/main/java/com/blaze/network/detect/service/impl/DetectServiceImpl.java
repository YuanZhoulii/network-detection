package com.blaze.network.detect.service.impl;

import com.blaze.network.common.constant.DetectConstant;
import com.blaze.network.common.utils.DateUtils;
import com.blaze.network.detect.feign.SaveFeignService;
import com.blaze.network.detect.feign.SearchFeignService;
import com.blaze.network.detect.service.DetectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 7:52
 */
@Slf4j
@Service
public class DetectServiceImpl implements DetectService {
    private static String curFilePath;
    @Autowired
    SearchFeignService searchFeignService;
    @Autowired
    SaveFeignService saveFeignService;
    @Override
    public void runNmap(String type,String host){
        Process process = null;
//        StringBuffer stringBuffer = new StringBuffer();
        try {
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
    }

    @Override
    public void detectAll() {
        Integer count = searchFeignService.count();
        for(int i=count;i>=1;i--){
            String address = searchFeignService.address(i);
            runNmap("all",address);
            saveFeignService.all(curFilePath);
        }
    }

    @Override
    public void detectOSByAddress(String address) {

        new Thread(()->{
            runNmap("os",address);
            saveFeignService.all(curFilePath);
        }).start();
    }

    @Override
    public void detectAllByAddress(String address) {
        new Thread(()->{
            runNmap("all",address);
            saveFeignService.all(curFilePath);
        }).start();
    }

    @Override
    public void detectHostByAddress(String address) {
        new Thread(()->{
            runNmap("status",address);
            saveFeignService.all(curFilePath);
        }).start();
    }

    @Override
    public String getExecParams(String type, String hosts) {
        //要输出的文件名：扫描IP+当前时间+XML扩展名（因为有些特殊字符比如*不能使用，暂不添加扫描IP到文件名中）
//        String fileName= hosts +"-"+ DateUtils.format(new Date(),DateUtils.CH_DATE_TIME_PATTERN)+ DetectConstant.XML_EXTENSION;
        //要输出的文件名：当前时间+XML扩展名
        String fileName= DateUtils.format(new Date(),DateUtils.CH_DATE_TIME_PATTERN)+ DetectConstant.XML_EXTENSION;
        //建立不同的文件夹存储当天的文件
        String today=DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
        //要保存文件的路径：总路径+今天日期+文件名
        String filePath=DetectConstant.XML_SAVE_PATH+ today+"\\"+fileName;
        curFilePath=filePath;
        //如果父目录不存在先创建
        File file=new File(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //指定保存参数
        String fileParam="-oX "+filePath;

        if("status".equals(type)){
            type = DetectConstant.PING_SCAN_PARAMS;
        }else if("os".equals(type)){
            type=DetectConstant.OS_SCAN_PARAMS;
        }
        else if("all".equals(type)){
            type =DetectConstant.ALL_SCAN_PARAMS;
        }
        String execParams=String.join(" ",new String[]{DetectConstant.NMAP_PATH, type,fileParam, hosts});
        return execParams;
    }
}
