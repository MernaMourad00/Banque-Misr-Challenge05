package com.banquemisr.challenge05.controller;

import com.banquemisr.challenge05.model.dto.JwtAuthResponse;
import com.banquemisr.challenge05.model.dto.LoginDto;
import com.banquemisr.challenge05.model.dto.RegisterDto;
import com.banquemisr.challenge05.service.serviceImpl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDTO) {
        String token = authServiceImpl.login(loginDTO);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }


    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDTO){
        String response = authServiceImpl.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
