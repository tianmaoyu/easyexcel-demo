package com.example.demo.tenum;
import com.example.demo.domain.User;
import com.example.demo.domain.UserType;
import com.example.demo.mapper.UserMapper;
import com.example.demo.tenum.SexEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MyBatisDatabaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAllUsers() {
        List<User> users = userMapper.selectList(null);
        assertThat(users).isNotEmpty();

        for (User user : users) {
            System.out.println("User: " + user.getName() + ", Sex: " + user.getSex());
            assertThat(user.getSex()).isIn(SexEnum.MALE, SexEnum.FEMALE,null);
        }
    }

    @Test
    public void testInsertUserWithEnum() {
        User user = new User();
        user.setName("John");
        user.setSex(SexEnum.MALE);
        user.setUserType(UserType.ADMIN);
        userMapper.insert(user);

        User insertedUser = userMapper.selectById(user.getId());
        assertThat(insertedUser.getSex()).isEqualTo(SexEnum.MALE);
        assertThat(insertedUser.getUserType()).isEqualTo(UserType.ADMIN);
    }

    @Test
    public void testInsertUserWithEnum_lambda() {
        User user = userMapper.selectById(2L);
        Boolean b = userMapper.updateEmail(".6666.com", 2L);
    }
}
