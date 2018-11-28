package com.pc.service;

import com.common.base.service.BaseService;
import com.pc.model.dto.AccountAuthDto;

import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 11:26
 */
public interface AccountAuthService extends BaseService<AccountAuthDto> {

    /**
     * 根据账户删除账户权限
     *
     * @param accountCode
     * @return
     */
    int deleteByAccountCode(String accountCode);

    /**
     * 根据权限删除账户权限
     *
     * @param authCode
     * @return
     */
    int deleteByAuthCode(String authCode);

    /**
     * 根据账户查询关联权限
     *
     * @param accountCode
     * @return
     */
    List<AccountAuthDto> listAuthCodeByAccountCode(String accountCode);

}