package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author eric
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-05-21 21:28:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Override
    public Boolean edit(User user) {
       return this.updateById(user);
    }
}




