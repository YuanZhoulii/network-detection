package com.blaze.network.save.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blaze.network.save.dao.HostDao;
import com.blaze.network.save.entity.HostEntity;
import com.blaze.network.save.entity.NmapTaskEntity;
import com.blaze.network.save.entity.PortEntity;
import com.blaze.network.save.service.HostService;
import com.blaze.network.save.service.NmapTaskService;
import com.blaze.network.save.service.PortService;
import com.blaze.network.save.service.SaveService;
import com.blaze.network.save.common.utils.XMLUtils;
import com.blaze.network.save.xml.*;
import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author yuanzhouli
 * @date 2021/4/1 - 6:25
 */
@Service
public class SaveServiceImpl implements SaveService {
    @Autowired
    private HostDao hostDao;
    @Autowired
    private HostService hostService;
    @Autowired
    private NmapTaskService nmapTaskService;
    @Autowired
    private PortService portService;
    @Autowired
    City city;

    @Override
    public void saveHostStatus(String filePath) {
        //xml文件转换为对象
        DetectHostStatus detectHostStatus = (DetectHostStatus) XMLUtils.xmlToObject(DetectHostStatus.class, filePath);
        //对应一条save_nmap_task（nmap任务）表记录
        NmapTaskEntity nmapTaskEntity = new NmapTaskEntity();
        //将第一个对象中与第二个对象同名的属性值复制到第二个对象中
        BeanUtils.copyProperties(detectHostStatus, nmapTaskEntity);
        //主机列表
        List<HostEntity> hostEntities = new ArrayList<>();
        for (Host host : detectHostStatus.getHostList()) {
            //对应一条save_host（主机）表记录
            HostEntity hostEntity = new HostEntity();
            BeanUtils.copyProperties(host.getAddress(), hostEntity);
            BeanUtils.copyProperties(host.getStatus(), hostEntity);
            hostEntities.add(hostEntity);
        }
        //批量插入多条主机记录
        hostService.saveBatch(hostEntities);
        Runstats runstats = detectHostStatus.getRunstats();
        BeanUtils.copyProperties(runstats.getFinished(), nmapTaskEntity);
        BeanUtils.copyProperties(runstats.getHosts(), nmapTaskEntity);
        nmapTaskService.save(nmapTaskEntity);
    }

    public void saveOrUpdateHost(HostEntity hostEntity) {
        Integer count = hostDao.selectCount(new QueryWrapper<HostEntity>().eq("address", hostEntity.getAddress()));
        if (count != null && count != 0) {
            hostService.update(hostEntity, new QueryWrapper<HostEntity>().eq("address", hostEntity.getAddress()));
        } else {
            hostService.save(hostEntity);
        }
    }

    @Override
    public void saveAllStatus(String filePath) {
        //xml文件转换为对象
        DetectAllStatus detectAllStatus =
                (DetectAllStatus) XMLUtils.xmlToObject(DetectAllStatus.class, filePath);
        System.out.println("detectAllStatus = " + detectAllStatus);
        //对应一条save_nmap_task（nmap任务）表记录
        NmapTaskEntity nmapTaskEntity = new NmapTaskEntity();
        BeanUtils.copyProperties(detectAllStatus, nmapTaskEntity);
        Runstats runstats = detectAllStatus.getRunstats();
        if (runstats != null) {
            BeanUtils.copyProperties(runstats.getFinished(), nmapTaskEntity);
            BeanUtils.copyProperties(runstats.getHosts(), nmapTaskEntity);
        }
        ScanInfo scanInfo = detectAllStatus.getScanInfo();
        if (scanInfo != null) {
            BeanUtils.copyProperties(scanInfo, nmapTaskEntity);
        }
        nmapTaskService.save(nmapTaskEntity);


        List<Host> hostList = detectAllStatus.getHostList();
        if (hostList != null) {
            List<HostEntity> hostEntities = new ArrayList<>();
            for (Host host : hostList) {
                //对应一条save_host（主机）表记录
                HostEntity hostEntity = new HostEntity();
                Address address = host.getAddress();
                Status status = host.getStatus();
                if (status != null) {
                    BeanUtils.copyProperties(status, hostEntity);
                }
                if(nmapTaskEntity!=null){
                    Integer nmapTaskId = nmapTaskEntity.getNmapTaskId();
                    if(nmapTaskId!=null){
                        hostEntity.setNmapTaskId(nmapTaskId);
                    }
                }
                hostEntity.setCreateTime(new Date());
                if (address != null) {
                    BeanUtils.copyProperties(address, hostEntity);
                    try {
                        CityInfo info = city.findInfo(address.getAddress(), "CN");
                        hostEntity.setArea(info.getRegionName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                OperatorSystem operatorSystem = host.getOperatorSystem();
                if (operatorSystem != null) {
                    List<OSMatch> osMatchList = operatorSystem.getOsMatchList();
                    if (!CollectionUtils.isEmpty(osMatchList)) {
                        osMatchList.sort(new Comparator<OSMatch>() {
                            @Override
                            public int compare(OSMatch o1, OSMatch o2) {
                                return o2.getAccuracy() - o1.getAccuracy();
                            }
                        });
                        String osName = osMatchList.get(0).getOsClass().getOsfamily();
                        hostEntity.setOs(osName);

                    }
                }
//                hostService.save(hostEntity);

                Ports ports = host.getPorts();
                if (ports != null) {
                    List<Port> portList = ports.getPortList();
                    if (portList != null) {
                        List<PortEntity> portEntities = new ArrayList<>();
                        for (Port port : portList) {
                            PortEntity portEntity = new PortEntity();
                            BeanUtils.copyProperties(port, portEntity);
                            com.blaze.network.save.xml.Service service = port.getService();
                            State portState = port.getPortState();
                            BeanUtils.copyProperties(portState, portEntity);
                            BeanUtils.copyProperties(service, portEntity);
                            portEntity.setHostId(hostEntity.getHostId());
                            portEntity.setHostAddress(hostEntity.getAddress());
                            portEntity.setCreateTime(new Date());
                            portEntities.add(portEntity);
                        }
                        portService.saveOrUpdateBatch(portEntities);
                    }
                }
                hostEntities.add(hostEntity);
            }
            //批量插入多条主机记录
            hostService.saveOrUpdateBatch(hostEntities);

        }

//        Host host = detectAllStatus.getHost();
//        Address address = host.getAddress();
//        String addr = address.getAddress();
//        HostEntity hostEntity = hostService.getOne(new QueryWrapper<HostEntity>().eq("address", addr));

//        if (host != null) {
//            OperatorSystem operatorSystem = host.getOperatorSystem();
//            if (operatorSystem != null) {
//                List<OSMatch> osMatchList = operatorSystem.getOsMatchList();
//                if (!CollectionUtils.isEmpty(osMatchList)) {
//                    osMatchList.sort(new Comparator<OSMatch>() {
//                        @Override
//                        public int compare(OSMatch o1, OSMatch o2) {
//                            return o2.getAccuracy() - o1.getAccuracy();
//                        }
//                    });
//                    String osName = osMatchList.get(0).getOsClass().getOsfamily();
//                    if(hostEntity==null){
//                        hostEntity=new HostEntity();
//                    }
//                    hostEntity.setOs(osName);
//                    hostService.save(hostEntity);
//                }
//            }
//
//
//            Ports ports = host.getPorts();
//            if (ports != null) {
//                List<Port> portList = ports.getPortList();
//                if (portList != null) {
//                    List<PortEntity> portEntities = new ArrayList<>();
//                    for (Port port : portList) {
//                        PortEntity portEntity = new PortEntity();
//                        BeanUtils.copyProperties(port, portEntity);
//                        com.blaze.network.save.xml.Service service = port.getService();
//                        State portState = port.getPortState();
//                        BeanUtils.copyProperties(portState, portEntity);
//                        BeanUtils.copyProperties(service, portEntity);
//                        if(hostEntity!=null){
//                            portEntity.setHostId(hostEntity.getHostId());
//                        }
//                        portEntities.add(portEntity);
//                    }
//                    portService.saveBatch(portEntities);
//                }
//            }
//
//
//        }


    }

    @Override
    public void saveOSStatus(String filePath) {
        //xml文件转换为对象
        DetectAllStatus detectAllStatus =
                (DetectAllStatus) XMLUtils.xmlToObject(DetectAllStatus.class, filePath);
        System.out.println("detectAllStatus = " + detectAllStatus);
        //对应一条save_nmap_task（nmap任务）表记录
        NmapTaskEntity nmapTaskEntity = new NmapTaskEntity();
        BeanUtils.copyProperties(detectAllStatus, nmapTaskEntity);
    }
}
