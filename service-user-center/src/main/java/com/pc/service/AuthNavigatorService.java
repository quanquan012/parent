package com.pc.service;

import com.common.base.service.BaseService;
import com.pc.model.dto.AuthNavigatorDto;

import java.util.List;

public interface AuthNavigatorService extends BaseService<AuthNavigatorDto> {
    /**
     * 根据权限编码删除关联导航
     *
     * @param authCode
     * @return
     */
    int deleteByAuthCode(String authCode);

    /**
     * 根据导航编码删除关联权限
     *
     * @param navigatorCode
     * @return
     */
    int deleteByNavigatorCode(String navigatorCode);

    /**
     * 根据权限编码查询关联导航
     *
     * @param authCodes
     * @return
     */
    List<AuthNavigatorDto> listNavigatorCodeByAuthCodes(List<String> authCodes);

}
