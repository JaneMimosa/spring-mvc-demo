package com.springmvc.demo.service;

import com.springmvc.demo.domain.Role;

public interface RoleService{
    Role findByName(String name);
}
