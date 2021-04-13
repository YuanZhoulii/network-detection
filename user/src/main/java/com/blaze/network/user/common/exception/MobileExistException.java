package com.blaze.network.user.common.exception;

/**
 * @author yuanzhouli
 * @date 2021/4/7 - 4:47
 */
public class MobileExistException extends RuntimeException {

    public MobileExistException() {
        super("手机已被注册");
    }
}
