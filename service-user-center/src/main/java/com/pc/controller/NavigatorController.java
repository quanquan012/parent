package com.pc.controller;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.controller.BaseController;
import com.pc.model.ao.NavigatorAo;
import com.pc.model.dto.NavigatorDto;
import com.pc.service.NavigatorService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
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
        return tree(service.selectList(searchConditions()));
    }

    private List<NavigatorDto> child(List<NavigatorDto> roots, List<NavigatorDto> list) {
        for (NavigatorDto dto : roots) {
            Iterator<NavigatorDto> iterator = list.iterator();
            List children = dto.getChildren();
            while (iterator.hasNext()){
                NavigatorDto child = iterator.next();
                if(dto.getNavigatorCode().equals(child.getParentCode())){
                    children.add(child);
                    iterator.remove();
                }
            }
            child(children, list);
        }

        return roots;
    }

    private List<NavigatorDto> tree(List<NavigatorDto> list) {
        List<NavigatorDto> roots = null;
        if (!list.isEmpty()) {
            roots = new ArrayList<>();
            Iterator<NavigatorDto> iterator = list.iterator();
            while (iterator.hasNext()) {
                NavigatorDto dto = iterator.next();
                if ("0".equals(dto.getParentCode())) {
                    roots.add(dto);
                    iterator.remove();
                }
            }
            roots = child(roots, list);
        }
        return roots;
    }

    @Override
    protected Conditions searchConditions(){
        Conditions conditions = new Conditions();
        conditions.addOrders(Order.asc(NavigatorAo.PRIORITY));
        return conditions;
    }

}
