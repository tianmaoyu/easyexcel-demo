package com.example.demo.tenum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SexEnum implements IEnum<Integer> {
    MALE(1, "男"),
    FEMALE(2, "女");


    private final Integer code;
    private final String desc;

    public static final SexEnum fromCode(Integer code){
        return Arrays.stream(SexEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
