package com.blaze.network.authserver.service.impl;

import com.blaze.network.authserver.feign.UserFeignService;
import com.blaze.network.authserver.service.AuthService;
import com.blaze.network.authserver.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yuanzhouli
 * @date 2021/2/22 - 18:14
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserFeignService userFeignService;

//    @GlobalTransactional
//    @Transactional
    @Override
    public void testSeata() {
        UserVo userVo = new UserVo();
        userVo.setUserId(2);
        userVo.setUsername("测试");
        userFeignService.update(userVo);
        jdbcTemplate.update("update my_test set name='张三' where id=1");





        int i=10/0;

    }
}
