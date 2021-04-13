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
public class PortFeignTo {
    private Integer portId;
    /**
     * 协议（tcp/udp等）
     */
    private String protocol;
    /**
     * 端口号
     */
    private Integer num;
    /**
     * 端口状态（open/close/filtered等）
     */
    private String state;
    /**
     * 保持端口状态原因（例如syn-ack）
     */
    private String stateReason;
    /**
     * ttl
     */
    private Integer stateReasonTtl;
    /**
     * 端口服务名
     */
    private String serviceName;
    /**
     * 对应主机id
     */
    private Integer hostId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建时间
     */
    private String hostAddress;
}
