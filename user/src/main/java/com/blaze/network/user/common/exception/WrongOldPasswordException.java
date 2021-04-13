package com.blaze.network.user.common.exception;

/**
 * @author yuanzhouli
 * @date 2021/4/7 - 5:04
 */
public class WrongOldPasswordException extends RuntimeException{

    public WrongOldPasswordException() {
        super("原密码错误");
    }
}
