package com.example.demo.service;

import com.example.demo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author eric
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-21 21:28:51
*/
public interface UserService extends IService<User> {

    Boolean edit(User user);

    Boolean editLambda(Long id, String name);
}
