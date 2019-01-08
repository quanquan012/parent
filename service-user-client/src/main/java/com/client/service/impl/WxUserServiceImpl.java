package com.client.service.impl;

import com.client.dao.WxUserDao;
import com.client.model.dto.WxUserDto;
import com.client.model.po.WxUser;
import com.client.service.WxUserService;
import com.common.base.service.impl.BaseServiceImpl;
import com.common.utils.model.CopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao
 * @date 2018-12-03 18:08
 */
@Service
public class WxUserServiceImpl extends BaseServiceImpl<WxUserDto, WxUser, WxUserDao> implements WxUserService {
    @Override
    public WxUserDto selectUserByOpenId(String openId) {
        WxUser po = new WxUser();
        po.setOpenId(openId);
        List<WxUser> list = mapper.select(po);
        return list.size() == 1 ? CopyUtils.copyObject(list.get(0), WxUserDto.class) : null;
    }
}