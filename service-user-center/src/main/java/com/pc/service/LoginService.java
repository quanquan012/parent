package com.pc.service;

import com.pc.model.dto.AccountDto;

public interface LoginService {

    AccountDto login(String account, String password);

}
