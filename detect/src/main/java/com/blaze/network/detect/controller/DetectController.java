package com.blaze.network.detect.controller;

import com.blaze.network.detect.service.DetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 7:49
 */
@RestController
@RequestMapping("detect/nmap")
public class DetectController {
    @Autowired
    DetectService detectService;

    @RequestMapping("/os/address")
    public void osByAddress(@RequestBody String address) {
        detectService.detectOSByAddress(address);
    }
    @RequestMapping("/host/address")
    public void hostByAddress(@RequestBody String address) {
        detectService.detectHostByAddress(address);
    }
    @RequestMapping("/all/address")
    public void allByAddress(@RequestBody String address) {
        detectService.detectAllByAddress(address);
    }
    @RequestMapping("/all/all")
    public void all() {
        detectService.detectAll();
    }

}
