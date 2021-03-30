package io.renren.modules.net.controller;


import com.alibaba.fastjson.TypeReference;
import com.blaze.network.common.exception.ErrorCodeEnum;
import com.blaze.network.common.utils.R;
import io.renren.modules.net.feign.UserFeignService;
import io.renren.modules.net.vo.UserManageSaveVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanzhouli
 * @date 2021/3/13 - 0:15
 */
@Slf4j
@RestController
@RequestMapping("net/user")
public class UserManageController {
    @Autowired
    private UserFeignService userFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("net:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        R r = userFeignService.list(params);
        return r;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("net:user:info")
    public R info(@PathVariable("userId") Integer userId) {
        R r = userFeignService.info(userId);
        UserManageSaveVo info = r.getData("info", new TypeReference<UserManageSaveVo>() {
        });
        return R.ok().put("info", info);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("net:user:save")
    public R save(@RequestBody @Valid UserManageSaveVo user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return R.error(ErrorCodeEnum.VAILD_EXCEPTION.getCode(), ErrorCodeEnum.VAILD_EXCEPTION.getMsg()).put("errors",errors);
        }
        return userFeignService.save(user);
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("net:user:delete")
    public R delete(@RequestBody Integer[] userIds) {
        return userFeignService.delete(userIds);
    }

    @RequestMapping("/check/username/{username}")
    public R checkUserNameUnique(@PathVariable("username") String username){
        return userFeignService.checkUserNameUnique(username);
    }
    @RequestMapping("/check/email/{email}")
    public R checkEmailUnique(@PathVariable("email") String email){
        return userFeignService.checkEmailUnique(email);
    }

}
