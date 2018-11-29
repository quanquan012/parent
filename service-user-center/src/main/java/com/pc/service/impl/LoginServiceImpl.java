package com.pc.service.impl;

import com.common.utils.model.CopyUtils;
import com.pc.dao.AccountDao;
import com.pc.model.dto.AccountDto;
import com.pc.model.po.Account;
import com.pc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * login
 *
 * @author li.hao
 * @date 2018/11/15 10:08
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountDao mapper;

    @Override
    public AccountDto login(String accountNumber, String accountPass) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        List<Account> accounts = mapper.select(account);
        if (!accounts.isEmpty() && accounts.size() == 1) {
            account = accounts.get(0);
            if (account.getAccountPass().equals(accountPass)) {
                return CopyUtils.copyObject(account, AccountDto.class);
            }
        }
        return null;
    }
}
