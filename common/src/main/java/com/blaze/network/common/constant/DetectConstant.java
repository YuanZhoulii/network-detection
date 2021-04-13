package com.blaze.network.common.constant;

/**
 * @author yuanzhouli
 * @date 2021/4/2 - 19:12
 */
public class DetectConstant {
    public static final String ALL_SCAN_PARAMS="-v -A -O --min-hostgroup 1024 --min-parallelism 1024";
    public static final String PING_SCAN_PARAMS="-v -sn -PE -n --min-hostgroup 1024 --min-parallelism 1024";
    public static final String OS_SCAN_PARAMS="-O -n --min-hostgroup 1024 --min-parallelism 1024";
    public static final String NMAP_PATH="D:\\Nmap\\nmap.exe";
    public static final String XML_SAVE_PATH="F:\\NmapResult\\";
    public static final String XML_EXTENSION=".xml";
}
