package org.example;

import com.fasterxml.jackson.annotation.JsonValue;
//import com.mybatisflex.annotation.EnumValue;


public interface IEnum<T>  {

//    @EnumValue
    @JsonValue
    T getCode();

    String getDesc();
}
