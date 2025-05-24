package com.example.demo.service;

import com.example.demo.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author eric
 * @description 针对表【account】的数据库操作Service
 * @createDate 2025-05-24 21:14:55
 */
public interface AccountService {

    Account selectById(Integer id);

    List<Account> selectAll();

    int insert(Account account);

    int update(Account account);

    int delete(Integer id);

}
