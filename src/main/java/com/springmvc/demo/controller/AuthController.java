package com.springmvc.demo.controller;

import com.springmvc.demo.config.jwt.JwtProvider;
import com.springmvc.demo.domain.User;
import com.springmvc.demo.domain.dto.AuthResponseDto;
import com.springmvc.demo.domain.dto.UserLoginDto;
import com.springmvc.demo.domain.dto.UserSignUpDto;
import com.springmvc.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public String registerUser(@RequestBody UserSignUpDto userSignUpDto) {
        User user = new User();
        user.setPassword(userSignUpDto.getPassword());
        user.setUsername(userSignUpDto.getUsername());
        user.setEmail(userSignUpDto.getEmail());
        userService.save(user);
        return "Ok";
    }

    @PostMapping("/login")
    public AuthResponseDto auth(@RequestBody UserLoginDto userLoginDto) {
        User user = userService.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponseDto(token);
    }
}
