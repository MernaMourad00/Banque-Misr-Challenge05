package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.model.dto.LoginDto;
import com.banquemisr.challenge05.model.dto.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String login(LoginDto loginDTO);
    String register(RegisterDto registerDTO);

}