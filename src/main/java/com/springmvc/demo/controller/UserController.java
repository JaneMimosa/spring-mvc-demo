package com.springmvc.demo.controller;

import com.springmvc.demo.domain.User;
import com.springmvc.demo.domain.dto.UserShowDto;
import com.springmvc.demo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final int pageSize = 20;

    @GetMapping
    public Page<UserShowDto> getAllUsers(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        return userServiceImpl.findAll(pageNum, pageSize);
    }

    @PutMapping
    public User enableUser(@RequestBody UserShowDto user, @RequestParam(value = "isEnable") boolean enable) {
        return userServiceImpl.setEnable(user.getUserId(), enable);
    }
}
