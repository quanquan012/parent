package com.pc.service.impl;

import com.common.base.condition.Conditions;
import com.common.base.condition.SearchFilter;
import com.common.base.service.impl.BaseServiceImpl;
import com.common.utils.conditions.ConditionsUtils;
import com.common.utils.model.CopyUtils;
import com.pc.dao.NavigatorDao;
import com.pc.model.consts.NavigatorConsts;
import com.pc.model.dto.NavigatorDto;
import com.pc.model.po.Navigator;
import com.pc.service.NavigatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigatorServiceImpl extends BaseServiceImpl<NavigatorDto, Navigator, NavigatorDao> implements NavigatorService {
    @Override
    public List<NavigatorDto> listNavigatorsByCodes(List<String> navigatorCodes) {
        Conditions conditions = new Conditions();
        conditions.addSearchFilters(SearchFilter.in(NavigatorConsts.NAVIGATOR_CODE, navigatorCodes));
        return CopyUtils.copyList(mapper.selectByExample(ConditionsUtils.getExampleByConditions(conditions, Navigator.class)), NavigatorDto.class);
    }
}
