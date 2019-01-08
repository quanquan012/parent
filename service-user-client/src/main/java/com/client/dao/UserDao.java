package com.client.dao;

import com.client.model.po.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDao extends Mapper<User> {

    void updateUserByOpenId(User user);

}
