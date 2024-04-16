package com.lxtyp.tobe.user.service.impl;

import com.lxtyp.tobe.user.entity.UserVO;
import com.lxtyp.tobe.user.mapper.UserMapper;
import com.lxtyp.tobe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public UserVO getUserVoById(String userId) {
        return userMapper.getUserById(userId);
    }
}
