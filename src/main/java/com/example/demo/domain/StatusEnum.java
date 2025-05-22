package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    SUCCESS(0, "操作成功"),
    ERROR(1, "系统异常");

    private final int code;
    private final String desc;
}