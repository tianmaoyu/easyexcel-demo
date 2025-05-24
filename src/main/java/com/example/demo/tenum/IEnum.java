package com.example.demo.tenum;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;


public interface IEnum<T>  {
    @JsonValue
    T getCode();

    String getDesc();
}
