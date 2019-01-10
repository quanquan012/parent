package com.pc.service.impl;

import com.common.base.service.impl.BaseServiceImpl;
import com.pc.dao.AccountDao;
import com.pc.model.dto.AccountDto;
import com.pc.model.po.Account;
import com.pc.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountDto, Account, AccountDao> implements AccountService {

}
