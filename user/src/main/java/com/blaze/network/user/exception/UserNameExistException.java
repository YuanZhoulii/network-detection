package com.blaze.network.user.exception;

/**
 * @author yuanzhouli
 * @date 2021/2/19 - 15:25
 */
public class UserNameExistException extends RuntimeException{
    public UserNameExistException() {
        super("用户名已存在");
    }
}
