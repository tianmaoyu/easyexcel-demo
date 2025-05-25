package org.example;

import com.fasterxml.jackson.annotation.JsonValue;


public interface IEnum<T>  {
    @JsonValue
    T getCode();

    String getDesc();
}
