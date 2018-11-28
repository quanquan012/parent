package com.pc.service;

import com.common.base.service.BaseService;
import com.pc.model.dto.NavigatorDto;

import java.util.List;

public interface NavigatorService extends BaseService<NavigatorDto> {
    /**
     * 根据导航编码查询导航
     *
     * @param navigatorCodes
     * @return
     */
    List<NavigatorDto> listNavigatorsByCodes(List<String> navigatorCodes);
}
