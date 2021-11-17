package com.springmvc.demo.service;

import com.springmvc.demo.domain.User;
import com.springmvc.demo.domain.dto.UserShowDto;
import org.springframework.data.domain.Page;

public interface UserService{

    User findByUsername(String username);

    User save(User user);

    Page<UserShowDto> findAll(int pageNum, int pageSize);

    User setEnable(Long userId, Boolean enable);

    User findByUsernameAndPassword(String username, String password);
}
