package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.Role;
import com.springmvc.demo.domain.User;
import com.springmvc.demo.domain.dto.UserShowDto;
import com.springmvc.demo.repository.RoleRepository;
import com.springmvc.demo.repository.UserRepository;
import com.springmvc.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<UserShowDto> findAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize)).map(UserShowDto::new);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username '%s' not found", username)));
    }

    @Override
    public User save(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User setEnable(Long userId, Boolean enable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        user.setEnabled(enable);

        userRepository.save(user);
        return  user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username);
        if(user != null) {
            if(passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
