package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public enum UserType {
    ADMIN("ADMIN","admin"),
    USER("USER","user");
    private String code;
    private String desc;
}
