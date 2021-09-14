package com.springmvc.demo.domain.dto;

import lombok.Data;

@Data
public class UserSignUpDto {

    private String username;

    private String email;

    private String password;
}
