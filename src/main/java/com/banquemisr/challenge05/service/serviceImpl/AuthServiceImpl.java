package com.banquemisr.challenge05.service.serviceImpl;

import com.banquemisr.challenge05.entity.Users;
import com.banquemisr.challenge05.exception.ConflictException;
import com.banquemisr.challenge05.exception.InvalidCredentialsException;
import com.banquemisr.challenge05.model.dto.LoginDto;
import com.banquemisr.challenge05.model.dto.RegisterDto;
import com.banquemisr.challenge05.repository.UserRepository;
import com.banquemisr.challenge05.security.JwtTokenProvider;
import com.banquemisr.challenge05.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(LoginDto loginDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsernameOrEmail(),
                            loginDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            return token;
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException("User not found with username or email : " + loginDTO.getUsernameOrEmail() +" password: "+loginDTO.getPassword());
        }
    }

    public String register(RegisterDto registerDTO) {

        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new ConflictException(HttpStatus.BAD_REQUEST,"username already exists");
        }

        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new ConflictException(HttpStatus.BAD_REQUEST,"email already exists");
        }

        Users user = new Users();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setRole(registerDTO.getRole());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(user);


        return "user registered successfully";
    }
}
