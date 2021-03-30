package io.renren.modules.net.feign;

import com.blaze.network.common.utils.R;
import io.renren.modules.net.vo.UserManageSaveVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yuanzhouli
 * @date 2021/3/13 - 0:07
 */
@FeignClient("user")
@RequestMapping("user/user")
public interface UserFeignService {
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params);
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id);
    @RequestMapping("/save")
    public R save(@RequestBody UserManageSaveVo info);
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids);
    @RequestMapping("/check/username/{username}")
    public R checkUserNameUnique(@PathVariable("username") String username);
    @RequestMapping("/check/email/{email}")
    public R checkEmailUnique(@PathVariable("email") String email);
}
