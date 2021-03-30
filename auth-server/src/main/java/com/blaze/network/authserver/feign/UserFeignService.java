package com.blaze.network.authserver.feign;

import com.blaze.network.authserver.vo.AuthLoginVo;
import com.blaze.network.authserver.vo.AuthRegistVo;
import com.blaze.network.authserver.vo.UserVo;
import com.blaze.network.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanzhouli
 * @date 2021/2/19 - 15:58
 */
@FeignClient("user")
@RequestMapping("user/user")
public interface UserFeignService {
    @PostMapping("/regist")
    public R regist(@RequestBody AuthRegistVo userRegistVo);
    @PostMapping("/login")
    public R login(@RequestBody AuthLoginVo userLoginVo);
    @RequestMapping("/update")
    public R update(@RequestBody UserVo info);
}
