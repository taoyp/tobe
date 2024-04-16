package com.lxtyp.tobe.user.mapper;

import com.lxtyp.tobe.user.entity.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserVO getUserById(@Param("userId") String userId);
}
