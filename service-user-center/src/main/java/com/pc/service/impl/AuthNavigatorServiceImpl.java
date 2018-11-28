package com.pc.service.impl;

import com.common.base.condition.Conditions;
import com.common.base.condition.SearchFilter;
import com.common.base.service.impl.BaseServiceImpl;
import com.common.utils.conditions.ConditionsUtils;
import com.common.utils.model.CopyUtils;
import com.pc.dao.AuthNavigatorDao;
import com.pc.model.consts.AuthConsts;
import com.pc.model.dto.AuthNavigatorDto;
import com.pc.model.po.AuthNavigator;
import com.pc.service.AuthNavigatorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 17:11
 */
@Service
public class AuthNavigatorServiceImpl extends BaseServiceImpl<AuthNavigatorDto, AuthNavigator, AuthNavigatorDao> implements AuthNavigatorService {
    @Override
    public int deleteByAuthCode(String authCode) {
        AuthNavigator authNavigator = new AuthNavigator();
        authNavigator.setAuthCode(authCode);
        return mapper.delete(authNavigator);
    }

    @Override
    public int deleteByNavigatorCode(String navigatorCode) {
        AuthNavigator authNavigator = new AuthNavigator();
        authNavigator.setNavigatorCode(navigatorCode);
        return mapper.delete(authNavigator);
    }

    @Override
    public List<AuthNavigatorDto> listNavigatorCodeByAuthCodes(List<String> authCodes) {
        Conditions conditions = new Conditions();
        conditions.addSearchFilters(SearchFilter.in(AuthConsts.AUTH_CODE, authCodes));
        return CopyUtils.copyList(mapper.selectByExample(ConditionsUtils.getExampleByConditions(conditions, AuthNavigator.class)), AuthNavigatorDto.class);
    }
}