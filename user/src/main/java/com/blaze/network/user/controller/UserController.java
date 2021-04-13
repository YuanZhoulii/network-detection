package com.blaze.network.user.controller;


import com.blaze.network.common.constant.ResponseConstant;
import com.blaze.network.common.exception.ErrorCodeEnum;
import com.blaze.network.common.exception.ErrorMsg;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;
import com.blaze.network.user.entity.UserEntity;
import com.blaze.network.user.common.exception.*;
import com.blaze.network.user.feign.GatewayFeignService;
import com.blaze.network.user.service.LoginLogService;
import com.blaze.network.user.service.UserService;
import com.blaze.network.user.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


/**
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-02-06 00:57:04
 */
//动态获取并刷新配置中心配置
@RefreshScope
@RestController
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    GatewayFeignService gatewayFeignService;

    //    @Value("${user.name}")
//    private String name;
//    @RequestMapping("/test")
//    public void test() {
//        System.out.println(name);
//        System.out.println("测试测试测试");
//    }

    @PostMapping("/regist")
    public R regist(@RequestBody UserRegistVo userRegistVo) {

        try {
            userService.regist(userRegistVo);
        } catch (EmailExistException e) {
            return R.error(ErrorCodeEnum.EMAIL_EXIST_EXCEPTION.getCode(), ErrorCodeEnum.EMAIL_EXIST_EXCEPTION.getMsg());
        }catch (UserNameExistException e){
            return R.error(ErrorCodeEnum.USER_EXIST_EXCEPTION.getCode(), ErrorCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }
        return R.ok(ResponseConstant.REGIST_SUCCESS);
    }

    @PostMapping("/login")
    public R login(@RequestBody UserLoginVo userLoginVo,HttpServletRequest request) {
        UserLoginRespVo info = userService.login(userLoginVo);
        if(info!=null){
            loginLogService.saveLoginLog(info.getUserId(),getRealIp(request));
            return R.ok(ResponseConstant.LOGIN_SUCCESS).setData(info);
        }else{
            return R.error(ErrorCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(), ErrorCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMsg());
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }

    public static String getRealIp(HttpServletRequest request) {
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        if (ip != null && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            ip = ipArray[0];
        }
        return ip;
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserEntity info = userService.getById(id);

        return R.ok().put("info", info);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserSaveVo info) {
        userService.save(info);

        return R.ok();
    }

    /**
     * 修改
     */
//    @Transactional
    @RequestMapping("/update")
    public R update(@RequestBody UserUpdateVo info) {
        UserLoginRespVo resp=null;
        ErrorMsg errorMsg=new ErrorMsg();
        try{
             resp= userService.myUpdateById(info);
        } catch (EmailExistException e) {
            errorMsg.setEmail(ErrorCodeEnum.EMAIL_EXIST_EXCEPTION.getMsg());
//            return R.error(ErrorCodeEnum.UPDATE_EXCEPTION.getCode(), ErrorCodeEnum.UPDATE_EXCEPTION.getMsg()).put("errors",errorMsg);
        }catch (MobileExistException e) {
            errorMsg.setMobile(ErrorCodeEnum.MOBILE_EXIST_EXCEPTION.getMsg());
//            return R.error(ErrorCodeEnum.UPDATE_EXCEPTION.getCode(), ErrorCodeEnum.UPDATE_EXCEPTION.getMsg()).put("errors",errorMsg);
        }catch (NicknameExistException e) {
            errorMsg.setNickname(ErrorCodeEnum.NICKNAME_EXIST_EXCEPTION.getMsg());
//            return R.error(ErrorCodeEnum.UPDATE_EXCEPTION.getCode(), ErrorCodeEnum.UPDATE_EXCEPTION.getMsg()).put("errors",errorMsg);
        }catch (WrongOldPasswordException e){
            errorMsg.setOldPassword(ErrorCodeEnum.WRONG_OLD_PASSWORD_EXCEPTION.getMsg());
//            return R.error(ErrorCodeEnum.UPDATE_EXCEPTION.getCode(), ErrorCodeEnum.UPDATE_EXCEPTION.getMsg()).put("errors",errorMsg);
        }catch (NewPasswordEqualsOldPasswordException e){
            errorMsg.setNewPassword(ErrorCodeEnum.NEW_EQ_OLD_PASSWORD_EXCEPTION.getMsg());
//            return R.error(ErrorCodeEnum.UPDATE_EXCEPTION.getCode(), ErrorCodeEnum.UPDATE_EXCEPTION.getMsg()).put("errors",errorMsg);
        }

        if(resp==null){
            return R.error(ErrorCodeEnum.UPDATE_EXCEPTION.getCode(), ErrorCodeEnum.UPDATE_EXCEPTION.getMsg()).put("errors",errorMsg);
        }
        return R.ok(ResponseConstant.UPDATE_SUCCESS).setData(resp);

    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 检查账号是否已存在
     */
    @RequestMapping("/check/username/{username}")
    public R checkUserNameUnique(@PathVariable("username") String username){
        try {
            userService.checkUserNameUnique(username);
        }catch (UserNameExistException e){
            return R.error(ErrorCodeEnum.USER_EXIST_EXCEPTION.getCode(), ErrorCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }
        return R.ok();
    }
    /**
     * 检查邮箱是否已存在
     */
    @RequestMapping("/check/email/{email}")
    public R checkEmailUnique(@PathVariable("email") String email){
        try {
            userService.checkEmailUnique(email);
        }catch (EmailExistException e){
            return R.error(ErrorCodeEnum.EMAIL_EXIST_EXCEPTION.getCode(), ErrorCodeEnum.EMAIL_EXIST_EXCEPTION.getMsg());
        }
        return R.ok();
    }

}
