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
public class HostFeignTo {
    private Integer hostId;
    /**
     * 主机地址
     */
    private String address;
    /**
     * 地址类型（IPV4/IPV6）
     */
    private String addressType;
    /**
     * 主机存活状态（up/down）
     */
    private String statusState;
    /**
     * 处于某种存活状态的原因
     */
    private String statusReason;
    /**
     * ICMP包的转发次数（跳数）
     */
    private Integer reasonTtl;
    /**
     * 所属地区
     */
    private String area;
    /**
     * 操作系统
     */
    private String os;
    /**
     * nmap任务id
     */
    private Integer nmapTaskId;
    /**
     * 创建时间
     */
    private Date createTime;
}
