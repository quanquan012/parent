package com.pc.service.impl;

import com.common.base.service.impl.BaseServiceImpl;
import com.common.utils.model.CopyUtils;
import com.pc.dao.AccountAuthDao;
import com.pc.model.dto.AccountAuthDto;
import com.pc.model.po.AccountAuth;
import com.pc.service.AccountAuthService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 11:30
 */
@Service
public class AccountAuthServiceImpl extends BaseServiceImpl<AccountAuthDto, AccountAuth, AccountAuthDao> implements AccountAuthService {
    @Override
    public int deleteByAccountCode(String accountCode) {
        AccountAuth accountAuth = new AccountAuth();
        accountAuth.setAccountCode(accountCode);
        return mapper.delete(accountAuth);
    }

    @Override
    public int deleteByAuthCode(String authCode) {
        AccountAuth accountAuth = new AccountAuth();
        accountAuth.setAuthCode(authCode);
        return mapper.delete(accountAuth);
    }

    @Override
    public List<AccountAuthDto> listAuthCodeByAccountCode(String accountCode) {
        AccountAuth accountAuth = new AccountAuth();
        accountAuth.setAccountCode(accountCode);
        return CopyUtils.copyList(mapper.select(accountAuth), AccountAuthDto.class);
    }
}