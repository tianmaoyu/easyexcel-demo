package com.example.demo.tenum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStatusTest {

    @Test
    void fromCode() {
        UserStatus userStatus = UserStatus.fromCode("enabled");
        assertEquals(UserStatus.ENABLED,userStatus);
    }

    @Test
    void getCode() {
    }

    @Test
    void getDesc() {
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }
}