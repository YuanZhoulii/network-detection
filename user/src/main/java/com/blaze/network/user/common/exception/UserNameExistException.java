package com.blaze.network.user.common.exception;

/**
 * @author yuanzhouli
 * @date 2021/2/19 - 15:25
 */
public class UserNameExistException extends RuntimeException{
    public UserNameExistException() {
        super("账号已被使用");
    }
}
