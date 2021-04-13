package io.renren.modules.net.service.impl;

import io.renren.modules.net.feign.DetectFeignService;
import io.renren.modules.net.service.NmapTaskService;
import io.renren.modules.net.vo.NmapTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanzhouli
 * @date 2021/4/10 - 3:11
 */
@Service
public class NmapTaskServiceImpl implements NmapTaskService {
    @Autowired
    DetectFeignService detectFeignService;

    @Override
    public void createNmapTask(NmapTaskVo nmapTaskVo) {
        new Thread(() -> {
            String scanType = nmapTaskVo.getScanType();
            String scanHost = nmapTaskVo.getScanHost();
            if ("status".equals(scanType)) {
                detectFeignService.hostByAddress(scanHost);
            } else if ("os".equals(scanType)) {
                detectFeignService.osByAddress(scanHost);
            } else if ("all".equals(scanType)) {
                detectFeignService.allByAddress(scanHost);

            }
        }).start();

    }
}