package com.example.demo.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author eric
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-05-21 21:28:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Boolean edit(User user) {
        return this.updateById(user);
    }

    @Override
    public Boolean editLambda(Long id, String name) {

        var date = parseStringToDate("2025-05-23 14:30:00");

        var update = lambdaUpdate()
                .eq(User::getId, id)
                .set(User::getName, name)
                .update(new User());

        return update;
    }
    @SneakyThrows
    public static Date parseStringToDate(String dateStr) {
        var sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);
    }
}




