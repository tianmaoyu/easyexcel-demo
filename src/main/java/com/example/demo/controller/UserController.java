package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserType;
import com.example.demo.param.UserAddParams;
import com.example.demo.service.UserService;
import com.example.demo.tenum.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService  userService;

    @GetMapping("/getById")
    public User getById(){
        User user = userService.getById(1L);
        return user;
    }

//    @GetMapping("/{enumName}")
//    public List<EnumDTO> getEnumList(@PathVariable String enumName) {
//        Class<Enum> enumClass = EnumUtil.getEnumClass(enumName);
//        return Arrays.stream(enumClass.getEnumConstants())
//                .map(e -> new EnumDTO(((IEnum<?>)e).getCode(), ((IEnum<?>)e).getDesc()))
//                .collect(Collectors.toList());
//    }
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

    @GetMapping("/getUserStatus")
    public ArrayList<UserStatus> getUserStatus(@RequestParam UserStatus userStatus){

        ArrayList<UserStatus> objects = new ArrayList<>();
        objects.add(userStatus);
        objects.add(UserStatus.ENABLED);
        objects.add(UserStatus.DISABLED);
        objects.add(UserStatus.LOCKED);
        return objects;


    }

    @SneakyThrows
    @PostMapping("/addUserStatus")
    public ArrayList<UserStatus> addUserStatus(@RequestBody ArrayList<UserStatus> userStatus){

        String jsonArray = objectMapper.writeValueAsString(userStatus);
        log.info("userStatus:{}",jsonArray);
        return userStatus;


    }

}
