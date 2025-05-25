package com.example.demo.controller;


import com.example.demo.domain.UserType;
import com.example.demo.tenum.SexEnum;
import com.example.demo.tenum.UserStatus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/TEnum")
public class TEnumController {
    @GetMapping("/getUserType")
    public UserType getUserType(@RequestParam("userType")UserType userType){
        return userType;
    }
    @GetMapping("/getSexEnum")
    public SexEnum getSexEnum(@RequestParam("sexEnum")SexEnum sexEnum){
        return sexEnum;
    }
    @GetMapping("/getUserStatus")
    public UserStatus getUserStatus(@RequestParam("userStatus") UserStatus userStatus){
        return userStatus;
    }

    @GetMapping("/getId")
    public Integer getOne(@RequestParam("id") Integer id){
        return id;
    }

}
