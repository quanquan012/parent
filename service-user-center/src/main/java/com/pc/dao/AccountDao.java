package com.pc.dao;

import com.pc.model.po.Account;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountDao extends Mapper<Account> {

}
