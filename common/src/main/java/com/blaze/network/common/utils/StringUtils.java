package com.blaze.network.common.utils;

/**
 * @author yuanzhouli
 * @date 2021/4/7 - 19:10
 */
public class StringUtils {
    public static boolean isNotBlank(String str){
        return str!=null&&!("".equals(str.trim()));
    }
}
