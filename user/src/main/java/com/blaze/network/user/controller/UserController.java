package com.blaze.network.user.controller;


import com.blaze.network.common.constant.ResponseConstant;
import com.blaze.network.common.exception.ErrorCodeEnum;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;
import com.blaze.network.user.entity.UserEntity;
import com.blaze.network.user.exception.EmailExistException;
import com.blaze.network.user.exception.UserNameExistException;
import com.blaze.network.user.feign.GatewayFeignService;
import com.blaze.network.user.service.UserService;
import com.blaze.network.user.vo.UserLoginRespVo;
import com.blaze.network.user.vo.UserLoginVo;
import com.blaze.network.user.vo.UserRegistVo;
import com.blaze.network.user.vo.UserSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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
    public R login(@RequestBody UserLoginVo userLoginVo) {
        UserLoginRespVo info = userService.login(userLoginVo);
        if(info!=null){
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
    public R update(@RequestBody UserEntity info) {

        userService.updateById(info);

        return R.ok();
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
