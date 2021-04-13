package com.blaze.network.detect.service;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 7:51
 */
public interface DetectService {
    String getExecParams(String type, String hosts);
    void runNmap(String type,String host);
    void detectAll();
    void detectOSByAddress(String address);
    void detectAllByAddress(String address);
    void detectHostByAddress(String address);
}
