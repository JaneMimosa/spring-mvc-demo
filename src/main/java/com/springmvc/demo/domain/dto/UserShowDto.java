package com.springmvc.demo.domain.dto;

import com.springmvc.demo.domain.Role;
import com.springmvc.demo.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserShowDto {

    Long userId;

    String username;

    String email;

    String role;

    Boolean isEnabled;

    public UserShowDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(","));
        this.isEnabled = user.isEnabled();
    }
}
