package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum UserType {
    ADMIN("002","admin"),
    USER("001","user");
    private String code;
    private String desc;
    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    // 可选：根据 code 反序列化枚举
    public static UserType fromCode(String code) {
        for (UserType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
