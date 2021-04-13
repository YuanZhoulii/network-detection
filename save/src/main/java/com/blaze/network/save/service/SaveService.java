package com.blaze.network.save.service;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:24
 */

public interface SaveService {
    void saveHostStatus(String filePath);
    void saveAllStatus(String filePath);

    void saveOSStatus(String filePath);
}
