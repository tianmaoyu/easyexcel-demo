package org.example.demomybtis.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.demomybtis.entity.Account;
import org.example.demomybtis.entity.AccountStatus;
import org.example.demomybtis.serivce.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AccountService userService;

    @GetMapping("/getById")
    public Account getById(){
        Account user = userService.selectById(1);
        return user;
    }

    @GetMapping("/addUserType")
    public AccountStatus addUserType(@RequestParam("userType") AccountStatus userType){
        return userType;
    }
//
//    @GetMapping("/getUserTypeList")
//    public List<AccountStatus> getUserTypeList(@RequestParam("userType") AccountStatus userType){
//        ArrayList<AccountStatus> objects = new ArrayList<>();
//        objects.add(AccountStatus.ENABLED);
//        objects.add(AccountStatus.UNDER_REVIEW);
//        return objects;
//    }
//
//    @GetMapping("/getUserStatus")
//    public ArrayList<AccountStatus> getUserStatus(@RequestParam AccountStatus userStatus){
//
//        ArrayList<AccountStatus> objects = new ArrayList<>();
//        objects.add(userStatus);
//        objects.add(AccountStatus.ENABLED);
//        objects.add(AccountStatus.DISABLED);
//        objects.add(AccountStatus.LOCKED);
//        return objects;
//
//
//    }
//
//    @SneakyThrows
//    @PostMapping("/addUserStatus")
//    public ArrayList<AccountStatus> addUserStatus(@RequestBody ArrayList<AccountStatus> userStatus){
//
//        String jsonArray = objectMapper.writeValueAsString(userStatus);
//        log.info("userStatus:{}",jsonArray);
//        return userStatus;
//
//
//    }

}
