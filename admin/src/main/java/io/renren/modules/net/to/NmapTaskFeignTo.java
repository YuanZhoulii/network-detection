package io.renren.modules.net.to;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/4/9 - 23:51
 */
@Data
@ToString
public class NmapTaskFeignTo {
    private Integer nmapTaskId;
    /**
     * 该任务执行的nmap命令
     */
    private String args;
    /**
     * 扫描类型（syn/ack等）
     */
    private String scanType;
    /**
     * 扫描协议（TCP/UDP等）
     */
    private String protocol;
    /**
     * 任务耗时
     */
    private String elapsed;
    /**
     * 任务开始时间
     */
    private Date startTime;
    /**
     * 任务结束时间
     */
    private Date finishTime;
    /**
     * 任务总结
     */
    private String summary;
    /**
     * 任务运行状态
     */
    private String runStatus;
    /**
     * 本次扫描存活主机数
     */
    private Integer hostsUp;
    /**
     * 本次扫描未存活主机数
     */
    private Integer hostsDown;
}
