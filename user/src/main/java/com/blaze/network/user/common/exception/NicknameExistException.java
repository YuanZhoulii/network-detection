package com.blaze.network.user.common.exception;

/**
 * @author yuanzhouli
 * @date 2021/4/7 - 3:59
 */
public class NicknameExistException extends RuntimeException{
    public NicknameExistException() {
        super("昵称已被使用");
    }
}
