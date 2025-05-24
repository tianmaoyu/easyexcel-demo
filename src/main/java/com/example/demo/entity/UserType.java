package com.example.demo.entity;

import com.example.demo.tenum.IEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UserType implements IEnum<String> {
    ADMIN("admin","admin"),
    USER("user","user");
    private final String code;
    private final String desc;
}
