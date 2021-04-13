package com.blaze.network.save.controller;

import com.blaze.network.common.utils.R;
import com.blaze.network.save.service.SaveService;
import com.blaze.network.save.xml.MySAXParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:20
 */
@RequestMapping("save/save")
@RestController
public class SaveController {
    @Autowired
    SaveService saveService;
    @Autowired
    MySAXParser mySAXParser;

    @RequestMapping("/status")
    public R status(String filePath){
        System.out.println("filepath："+filePath);
        saveService.saveHostStatus(filePath);
        return R.ok();
    }
    @RequestMapping("/os")
    public R os(String filePath){
        System.out.println("filepath："+filePath);
//        saveService.saveAllStatus(filePath);
        return R.ok();
    }
    @RequestMapping("/all")
    public R all(@RequestBody String filePath){
        System.out.println("filepath："+filePath);
        saveService.saveAllStatus(filePath);
        return R.ok();
    }


    @RequestMapping("/save")
    public R save(){
        mySAXParser.parse("C:\\Users\\微星\\Desktop\\上次扫描结果\\nmap_output.xml");
        return R.ok();
    }

}
