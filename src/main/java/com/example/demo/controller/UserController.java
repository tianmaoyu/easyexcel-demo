package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserType;
import com.example.demo.param.UserAddParams;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/addList")
    public Boolean addList(@RequestParam("count") Integer count){
       return userService.addList(count);
    }
    //edit
    @PostMapping("/edit")
    public Integer edit(@RequestBody User user){
       return userService.edit(user);
    }
    @PostMapping("/edit2")
    public Integer edit2( User user){
        return userService.edit(user);
    }
    @PostMapping("/editLambda")
    public Boolean editLambda(@RequestParam("name") String name,@RequestParam("userId") Long userId){
        return userService.editLambda(userId,  name);
    }

    @GetMapping("/addUserType")
    public UserType addUserType(@RequestParam("userType") UserType userType){
        return userType;
    }
    @GetMapping("/getUserTypeList")
    public List<UserType> getUserTypeList(@RequestParam("userType") UserType userType){
        ArrayList<UserType> objects = new ArrayList<>();
        objects.add(UserType.USER);
        objects.add(UserType.ADMIN);
        return objects;
    }
}
