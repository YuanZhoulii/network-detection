package com.blaze.network.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanzhouli
 * @date 2021/2/7 - 20:39
 */
@RequestMapping("/test")
@RestController
public class TestController {
    @RequestMapping("/test")
    public void test() {
        System.out.print("TestController.mytest");
    }
}
