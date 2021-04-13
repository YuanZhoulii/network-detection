package com.blaze.network.authserver.controller;

import com.alibaba.fastjson.TypeReference;
import com.blaze.network.authserver.feign.LoginLogFeignService;
import com.blaze.network.authserver.feign.UserFeignService;
import com.blaze.network.authserver.vo.AuthLoginVo;
import com.blaze.network.authserver.vo.AuthRegistVo;
import com.blaze.network.authserver.vo.AuthUpdateVo;
import com.blaze.network.common.constant.LogConstant;
import com.blaze.network.common.constant.ResponseConstant;
import com.blaze.network.common.constant.SessionConstant;
import com.blaze.network.common.exception.ErrorCodeEnum;
import com.blaze.network.common.utils.R;
import com.blaze.network.common.vo.UserResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanzhouli
 * @date 2021/2/17 - 22:48
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserFeignService userFeignService;
    @Autowired
    LoginLogFeignService loginLogFeignService;
    @RequestMapping("/update")
    public R update(@RequestBody @Valid AuthUpdateVo vo,BindingResult result,HttpSession session){
        log.info(vo.getClass().getName()+":"+ vo);
        if(result.hasErrors()){
            log.error(LogConstant.UPDATE_FAIL);
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return R.error(ErrorCodeEnum.VAILD_EXCEPTION.getCode(), ErrorCodeEnum.VAILD_EXCEPTION.getMsg()).put("errors",errors);
        }
        //调用远程服务进行更新
        R r = userFeignService.update(vo);
        if (r.getCode() == 0) {
            //更新成功
            UserResponseVo data = r.getData("data", new TypeReference<UserResponseVo>() {
            });
            //将当前用户信息放在session中
            session.setAttribute(SessionConstant.LOGIN_USER,data);
            log.info(LogConstant.UPDATE_SUCCESS);
        } else {
            log.error(LogConstant.UPDATE_FAIL);
        }

        return r;
    }

    /**
     * @Description 获取登录用户信息
     * @date 2021-02-20
     * @param session
     * @return javax.servlet.http.HttpSession
     */
    @GetMapping("/current")
    public R current(HttpSession session){

        UserResponseVo user = (UserResponseVo) session.getAttribute(SessionConstant.LOGIN_USER);
        if(user==null){
            return R.error(ErrorCodeEnum.UNLOGIN_EXCEPTION.getCode(), ErrorCodeEnum.UNLOGIN_EXCEPTION.getMsg());
        }
        return R.ok(ResponseConstant.ALREADY_LOGIN).setData(user);
    }

    @GetMapping("/exit")
    public R exit(HttpSession session){
        try{
//            session.invalidate();
            session.removeAttribute(SessionConstant.LOGIN_USER);
            return R.ok(ResponseConstant.LOGOUT_SUCCESS);
        }catch (Exception e){
            return R.error(ResponseConstant.LOGOUT_FAIL);
        }
    }


    @PostMapping("/regist")
    public R regist(@RequestBody @Valid AuthRegistVo authRegistVo, BindingResult result){
        log.info("authRegistVo:"+ authRegistVo);
        //参数校验出错
        if(result.hasErrors()){
            log.error(LogConstant.REGIST_FAIL);
            //将错误消息封装为map
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return R.error(ErrorCodeEnum.VAILD_EXCEPTION.getCode(), ErrorCodeEnum.VAILD_EXCEPTION.getMsg()).put("errors",errors);
        }
        //调用远程服务进行注册
        R r = userFeignService.regist(authRegistVo);


        if (r.getCode() == 0) {
            log.info(LogConstant.REGIST_SUCCESS);
        } else {
            log.error(LogConstant.REGIST_FAIL);
        }

        return r;
    }

    @PostMapping("/login")
    public R login(@RequestBody @Valid AuthLoginVo authLoginVo, BindingResult result, HttpSession session, HttpServletRequest request){
        //参数校验出错
        if(result.hasErrors()){
            log.error(LogConstant.LOGIN_FAIL);
            //将错误消息封装为map
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return R.error(ErrorCodeEnum.VAILD_EXCEPTION.getCode(), ErrorCodeEnum.VAILD_EXCEPTION.getMsg()).put("errors",errors);
        }

        //远程用户服务方法
        R r = userFeignService.login(authLoginVo);

        if (r.getCode() == 0) {
            //登录成功
            UserResponseVo data = r.getData("data", new TypeReference<UserResponseVo>() {
            });
            //将当前用户信息放在session中
            session.setAttribute(SessionConstant.LOGIN_USER,data);
            log.info(LogConstant.LOGIN_SUCCESS);
        } else {
            log.error(LogConstant.LOGIN_FAIL);
        }
        return r;
    }

}
