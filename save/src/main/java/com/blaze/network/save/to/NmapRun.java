package com.blaze.network.save.to;

/**
 * @author yuanzhouli
 * @date 2021/3/17 - 20:32
 */

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 一次Nmap的扫描结果
 */
@Data
@ToString
@XmlRootElement(name = "nmaprun")
@XmlAccessorType(XmlAccessType.FIELD)
public class NmapRun {
    /**
     * 扫描软件?
     * 示例：nmap
     */
    @XmlAttribute
    private String scanner;
    /**
     * Nmap查询命令
     * 示例:nmap -v -sn -PE -n --min-hostgroup 1024 --min-parallelism 1024 -oX nmap_output.xml 1.0.1.0/24
     */
    @XmlAttribute
    private String args;
    /**
     * 开始时间戳
     * 示例：1615983925
     */
    @XmlAttribute
    private String start;
    /**
     * 开始时间日期
     * 示例：Wed Mar 17 20:25:25 2021
     */
    @XmlAttribute
    private String startstr;
    /**
     * Nmap版本
     * 示例：7.91
     */
    @XmlAttribute
    private String version;
    /**
     * xml输出版本
     * 示例：1.05
     */
    @XmlAttribute
    private String xmloutputversion;
    @XmlElement(name = "verbose")
    private Verbose verbose;
    @XmlElement(name = "debugging")
    private Debugging debugging;
    @XmlElement(name="taskbegin")
    private TaskBegin taskBegin;
    @XmlElement(name = "taskend")
    private TaskEnd taskEnd;
    /**
     * 使用List映射多个host标签
     */
    @XmlElement(name="host")
    private List<Host> hostList;
    /**
     * 本次Nmap扫描结果统计信息
     */
    @XmlElement(name="runstats")
    private Runstats runstats;


    @Data
    @ToString
    @XmlRootElement(name = "verbose")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Verbose {
        /**
         * verbose日志输出级别？
         * 示例：1
         */
        @XmlAttribute
        private String level;
    }
    @Data
    @ToString
    @XmlRootElement(name = "debugging")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Debugging {
        @XmlAttribute
        private String level;
    }
    @Data
    @ToString
    @XmlRootElement(name = "taskbegin")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class TaskBegin {
        /**
         * 示例：Ping Scan
         */
        @XmlAttribute
        private String task;
        /**
         * 示例：1615983926
         */
        @XmlAttribute
        private String time;
    }
    @Data
    @ToString
    @XmlRootElement(name = "taskend")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class TaskEnd {
        /**
         * 示例：Ping Scan
         */
        @XmlAttribute
        private String task;
        /**
         * 示例：1615983928
         */
        @XmlAttribute
        private String time;
        /**
         * 示例：256 total hosts
         */
        @XmlAttribute
        private String extrainfo;
    }

    @Data
    @ToString
    @XmlRootElement(name = "host")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Host {
        @XmlElement(name = "status")
        private Status status;
        @XmlElement(name = "address")
        private Address address;
        @Data
        @ToString
        @XmlRootElement(name = "status")
        @XmlAccessorType(XmlAccessType.FIELD)
        private static class Status {
            @XmlAttribute
            private String state;
            @XmlAttribute
            private String reason;
            @XmlAttribute(name = "reason_ttl")
            private String reason_ttl;
        }
        @Data
        @ToString
        @XmlRootElement(name = "address")
        @XmlAccessorType(XmlAccessType.FIELD)
        private static class Address {
            /**
             * 扫描地址
             * 示例：1.0.1.1
             */
            @XmlAttribute
            private String addr;
            /**
             * 地址类型
             * 示例：ipv4
             */
            @XmlAttribute
            private String addrtype;
        }
    }

    @Data
    @ToString
    @XmlRootElement(name = "runstats")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Runstats {
        @XmlElement(name="finished")
        private Finished finished;
        @XmlElement(name = "hosts")
        private Hosts hosts;

        @Data
        @ToString
        @XmlRootElement(name = "finished")
        @XmlAccessorType(XmlAccessType.FIELD)
        private static class Finished {
            @XmlAttribute
            private String time;
            @XmlAttribute
            private String timestr;
            @XmlAttribute
            private String summary;
            @XmlAttribute
            private String elapsed;
            @XmlAttribute
            private String exit;

        }
        @Data
        @ToString
        @XmlRootElement(name = "hosts")
        @XmlAccessorType(XmlAccessType.FIELD)
        private static class Hosts {
            /**
             * 主机存活总数
             */
            @XmlAttribute
            private String up;
            /**
             * 主机未存活总数
             */
            @XmlAttribute
            private String down;
            /**
             * 扫描主机总数
             */
            @XmlAttribute
            private String total;
        }
    }
}
