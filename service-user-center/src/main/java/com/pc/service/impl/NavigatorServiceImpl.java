package com.pc.service.impl;

import com.common.base.service.impl.BaseServiceImpl;
import com.pc.dao.NavigatorDao;
import com.pc.model.dto.NavigatorDto;
import com.pc.model.po.Navigator;
import com.pc.service.NavigatorService;
import org.springframework.stereotype.Service;

@Service
public class NavigatorServiceImpl extends BaseServiceImpl<NavigatorDto, Navigator, NavigatorDao> implements NavigatorService {
}
