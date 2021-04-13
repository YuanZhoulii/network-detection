package com.blaze.network.user.common.exception;

/**
 * @author yuanzhouli
 * @date 2021/2/19 - 15:26
 */
public class EmailExistException extends RuntimeException{
    public EmailExistException() {
        super("邮箱已被使用");
    }
}
