package com.example.demo.tenum;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.TypeDescriptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.util.Assert.isInstanceOf;

class TEnumConverterTest {
    // 测试字符串转枚举
    @Test
    void testStringToEnum() {
        TEnumConverter converter = new TEnumConverter();
        Object result = converter.convert("1", TypeDescriptor.valueOf(String.class),
                TypeDescriptor.valueOf(SexEnum.class));
        assertThat(result).isEqualTo(SexEnum.MALE);

        Object result1 = converter.convert("enabled", TypeDescriptor.valueOf(String.class),
                TypeDescriptor.valueOf(UserStatus.class));
        assertThat(result1).isEqualTo(UserStatus.ENABLED);

    }

    @Test
    void testStringToEnum_UserStatus() {
        TEnumConverter converter = new TEnumConverter();

        UserStatus result1 = (UserStatus)converter.convert("enabled", TypeDescriptor.valueOf(String.class),
                TypeDescriptor.valueOf(UserStatus.class));


        assert(UserStatus.ENABLED==result1);
//        assertThat(result1).isEqualTo(UserStatus.ENABLED);

    }

    // 测试数字转枚举
    @Test
    void testIntegerToEnum() {
        TEnumConverter converter = new TEnumConverter();
        Object result = converter.convert(2, TypeDescriptor.valueOf(Integer.class),
                TypeDescriptor.valueOf(SexEnum.class));
        assertThat(result).isEqualTo(SexEnum.FEMALE);
    }



    // 测试非法值
    @Test
    void testInvalidValue() {
        TEnumConverter converter = new TEnumConverter();

    }
}