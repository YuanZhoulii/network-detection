package com.blaze.network.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blaze.network.user.entity.UserEntity;
import com.blaze.network.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        UserEntity userEntity =new UserEntity();
        userEntity.setEmail("yuanzhouli1997@163.com");
        userEntity.setGender("男");
        userEntity.setPassword("123456");
        userEntity.setUsername("yuanzhouli");
        userEntity.setCreateTime(new Date());
        userEntity.setNickname("无晨莫阳");
        userEntity.setMobile("17777777777");
        userService.save(userEntity);
    }
    @Test
    void updateTest(){
        UserEntity userEntity =new UserEntity();
        userEntity.setUserId(1);
        userEntity.setEmail("yuanzhouli1997@163.com");
        userEntity.setGender("男");
        userEntity.setPassword("123456789");
        userEntity.setUsername("yuanzhouli");
        userEntity.setCreateTime(new Date());
        userEntity.setNickname("无晨莫阳");
        userEntity.setMobile("17777777777");
        userService.updateById(userEntity);
    }
    @Test
    void selectByIdTest(){
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("user_id", 1));
        System.out.println(userEntity);
    }
    @Test
    void selectList(){
        List<UserEntity> infoEntities = userService.list(new QueryWrapper<UserEntity>().eq("nickname", "无晨莫阳"));
        infoEntities.forEach(System.out::println);
    }
    @Test
    void deleteByid(){
        boolean b = userService.removeById(1);
        System.out.println(b?"删除成功":"删除失败");
    }


}
