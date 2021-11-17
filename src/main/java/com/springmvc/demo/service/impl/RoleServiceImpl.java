package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.Role;
import com.springmvc.demo.repository.RoleRepository;
import com.springmvc.demo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
