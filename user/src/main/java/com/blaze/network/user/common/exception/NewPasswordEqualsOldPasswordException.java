package com.blaze.network.user.common.exception;

/**
 * @author yuanzhouli
 * @date 2021/4/7 - 5:26
 */
public class NewPasswordEqualsOldPasswordException extends RuntimeException{
    public NewPasswordEqualsOldPasswordException() {
        super("新密码与原密码相同！");
    }
}
