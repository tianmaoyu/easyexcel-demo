package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.param.UserAddParams;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService  userService;

    @GetMapping("/getById")
    public User getById(){
        User user = userService.getById(1L);
        return user;
    }

    //add
    @PostMapping("/add")
    public User add(@RequestBody UserAddParams addParams){
        User user = new User();
        user.setName("test");
        user.setAge(18);
        user.setEmail("test@test.com");
        userService.save(user);
        return user;
    }
    //edit
    @PostMapping("/edit")
    public Boolean edit(User user){
       return userService.edit(user);
    }
}
