package org.example.flex.serivce;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.flex.entity.Account;
import org.example.flex.entity.AccountStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private  AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void insert() {
        Account account = new Account();
        account.setUserName("test");
        account.setAge(18);
        account.setBirthday(LocalDateTime.now().plusYears(-18));
//        account.setAccountStatus(AccountStatus.ENABLED);
        int row = accountService.insert(account);
        assertEquals(row,1);

    }

    @Test
    void selectById() {

        Account account = accountService.selectById(3);
        assertEquals(account.getId(),3);
        assertNotNull(account);

    }

    @Test
    void selectAll() {
        List<Account> accounts = accountService.selectAll();
        assert (accounts.size()>0);
    }



    @Test
    void update() {

        Account account = accountService.selectById(3);
        account.setUserName("test");
        int row = accountService.update(account);
        assertEquals(row,1);
    }

    @Transactional
    @Test
    void delete() {
        int row = accountService.delete(3);
        assertEquals(row,1);
    }
}