package org.example.flex.serivce;


import org.example.flex.entity.Account;
import org.example.flex.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author eric
* @description 针对表【account】的数据库操作Service实现
* @createDate 2025-05-24 21:14:55
*/
@Service
public class AccountServiceImpl implements AccountService {

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
        return accountMapper.deleteById(id);
    }

    public Boolean deleteByName(String name) {
        return accountMapper.deleteByName(name);
    }
}




