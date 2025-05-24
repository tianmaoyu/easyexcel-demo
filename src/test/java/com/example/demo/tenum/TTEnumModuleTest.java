package com.example.demo.tenum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TTEnumModuleTest {
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new TEnumModule());
    }

    @Test
    void testSerialize() throws JsonProcessingException {
        // 序列化测试：枚举 -> code
        String json = objectMapper.writeValueAsString(SexEnum.MALE);
        assert("1".equals(json));
        assertEquals(Integer.parseInt(json), 1);
    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        // 反序列化测试：code -> 枚举
        String json = "\"1\"";
        SexEnum result = objectMapper.readValue(json, SexEnum.class);
        assertEquals(SexEnum.MALE, result);
    }

    @Test
    void testRoundTrip() throws JsonProcessingException {
        // 序列化 + 反序列化双向验证
        String userStatus = objectMapper.writeValueAsString(UserStatus.ENABLED);

        UserStatus original = UserStatus.ENABLED;
        String json = objectMapper.writeValueAsString(original);
        UserStatus deserialized = objectMapper.readValue(json, UserStatus.class);
        assertEquals(original, deserialized);
    }
}
