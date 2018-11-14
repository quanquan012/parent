package com.pc.controller;

import com.common.base.controller.BaseController;
import com.pc.model.ao.NavigatorAo;
import com.pc.model.dto.NavigatorDto;
import com.pc.service.NavigatorService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航
 *
 * @author li.hao
 * @date 2018/11/14 16:56
 */
@RestController("/navigators")
public class NavigatorController extends BaseController<NavigatorAo, NavigatorDto, NavigatorService> {
    @Override
    protected List<NavigatorDto> listAll() {
        return service.selectList(searchConditions());
    }

    private List<NavigatorDto> tree(List<NavigatorDto> list) {
        ArrayList<NavigatorDto> children;
        ArrayList<NavigatorDto> newList;
        if (list.isEmpty()) {
            newList = new ArrayList<>();
            for (NavigatorDto dto : list) {
                if("0".equals(dto.getNavigatorCode())){

                }
            }
            children = new ArrayList<>();
        }

        return null;
    }
}
