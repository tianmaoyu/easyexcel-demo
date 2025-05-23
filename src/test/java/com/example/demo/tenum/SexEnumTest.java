package com.example.demo.tenum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SexEnumTest {

    @Test
    void getCode() {
        assertEquals(1, SexEnum.MALE.getCode());
        SexEnum sexEnum = SexEnum.fromCode(1);
        assertEquals(SexEnum.MALE, sexEnum);
    }

    @Test
    void getDesc() {

    }

    @Test
    void values() {

    }
}