package com.blaze.network.common.exception;

/***
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5位数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *  11: 用户
 */
public enum ErrorCodeEnum {

    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    TOO_MANY_REQUEST(10002,"请求流量过大，请稍后再试"),
    SMS_CODE_EXCEPTION(10003,"验证码获取频率太高，请稍后再试"),
    READ_TIME_OUT_EXCEPTION(10004,"远程调用服务超时，请重新再试"),
    SECKILL_EXCEPTION(10005,"秒杀请求过多，请重新再试"),
    USER_EXIST_EXCEPTION(11001,"该账号已被注册"),
    EMAIL_EXIST_EXCEPTION(11002,"该邮箱已被使用"),
    LOGINACCT_PASSWORD_EXCEPTION(11003,"账号或密码错误"),
    UNLOGIN_EXCEPTION(11004,"您尚未登录");
    ;

    private int code;
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}