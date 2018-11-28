package com.pc.service.impl;

import com.common.base.service.impl.BaseServiceImpl;
import com.pc.dao.AuthDao;
import com.pc.model.dto.AuthDto;
import com.pc.model.po.Auth;
import com.pc.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 * @date 2018-11-22 13:20
 */
@Service
public class AuthImpl extends BaseServiceImpl<AuthDto, Auth, AuthDao> implements AuthService {

}