package com.example.demo.tenum;

import com.example.demo.tenum.MyBatisAutoConfiguration;
import com.example.demo.tenum.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyBatisAutoConfigurationTest {

    private MyBatisAutoConfiguration myBatisAutoConfiguration;

    @Mock
    private TypeHandlerRegistry registry;

    @BeforeEach
    public void setUp() {
        myBatisAutoConfiguration = new MyBatisAutoConfiguration();
    }

    @Test
    public void testResolveCodeType() {
        Class<?> codeType = myBatisAutoConfiguration.resolveCodeType(SexEnum.class);
        assertEquals(Integer.class, codeType);
    }

    @Test
    public void testResolveCodeType_string() {
        Class<?> codeType = myBatisAutoConfiguration.resolveCodeType(UserStatus.class);
        assertEquals(String.class, codeType);
    }

    @Test
    public void testResolveJdbcType_Integer() {
        JdbcType jdbcType = myBatisAutoConfiguration.resolveJdbcType(Integer.class);
        assertEquals(JdbcType.INTEGER, jdbcType);
    }

    @Test
    public void testResolveJdbcType_String() {
        JdbcType jdbcType = myBatisAutoConfiguration.resolveJdbcType(String.class);
        assertEquals(JdbcType.VARCHAR, jdbcType);
    }

    @Test
    public void testResolveJdbcType_Long() {
        JdbcType jdbcType = myBatisAutoConfiguration.resolveJdbcType(Long.class);
        assertEquals(JdbcType.BIGINT, jdbcType);
    }

    @Test
    public void testResolveJdbcType_Default() {
        JdbcType jdbcType = myBatisAutoConfiguration.resolveJdbcType(Double.class);
        assertEquals(JdbcType.OTHER, jdbcType);
    }
}
