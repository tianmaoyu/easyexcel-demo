package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@TEnum
@AllArgsConstructor
public enum UserType {
    ADMIN("admin","admin"),
    USER("user","user");
    private String code;
    private String desc;
}
