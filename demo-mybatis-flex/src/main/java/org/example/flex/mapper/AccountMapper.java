package org.example.flex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.ChainQuery;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import org.apache.ibatis.annotations.Mapper;
import org.example.flex.entity.Account;

import java.util.List;

/**
* @author eric
* @description 针对表【account】的数据库操作Mapper
* @createDate 2025-05-24 21:14:55
* @Entity com.example.demo.domain.Account
*/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    default Account selectById(int id){
        Account account =  QueryChain.of(this)
                .eq(Account::getId, id)
                .select()
                .limit(1)
                .one();
        return account;

    }
     default Boolean deleteByName(String name){

         var queryChain = QueryChain.of(this)
                 .eq(Account::getUserName, name);

         int i = this.deleteByQuery(queryChain);
         return i > 0;
     }
}




