package com.lxtyp.tobe.user.controller;

import com.lxtyp.tobe.common.TobeConst;
import com.lxtyp.tobe.user.entity.UserVO;
import com.lxtyp.tobe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TobeConst.REST_V2 + "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/id")
    public UserVO getUserById(@RequestParam("userId") String userId) {
        UserVO userVO = userService.getUserVoById(userId);
        System.out.println(userVO);
        return userVO;
    }
}
