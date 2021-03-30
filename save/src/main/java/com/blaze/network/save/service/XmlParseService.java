package com.blaze.network.save.service;

import com.blaze.network.save.to.NmapRun;

/**
 * 城市数据接口
 */
public interface XmlParseService {
 
    NmapRun listCity() throws Exception;
}