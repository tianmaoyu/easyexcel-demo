package org.example.demomybtis.serivce;


import org.example.demomybtis.entity.Account;
import org.example.demomybtis.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author eric
* @description 针对表【account】的数据库操作Service实现
* @createDate 2025-05-24 21:14:55
*/
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account selectById(Integer id) {
        return accountMapper.selectById(id);
    }
    @Override
    public List<Account> selectAll() {
        return accountMapper.selectAll();
    }
    @Override
    public int insert(Account account) {
        return accountMapper.insert(account);
    }
    @Override
    public int update(Account account) {
        return accountMapper.update(account);
    }
    @Override
    public int delete(Integer id) {
        return accountMapper.delete(id);
    }
}




