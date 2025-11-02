package com.ben.Access.Service.service.impl;

import com.ben.Access.Service.entity.Otp;
import com.ben.Access.Service.entity.User;
import com.ben.Access.Service.exception.UserException;
import com.ben.Access.Service.mapper.UserMapper;
import com.ben.Access.Service.model.dto.UserDto;
import com.ben.Access.Service.model.response.AuthResponse;
import com.ben.Access.Service.repo.OtpRepo;
import com.ben.Access.Service.repo.UserRepo;
import com.ben.Access.Service.security.CustomUserDetailService;
import com.ben.Access.Service.security.JwtProvider;
import com.ben.Access.Service.service.AuthService;
import com.ben.Access.Service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final CustomUserDetailService customUserDetailService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final OtpRepo otpRepo;

    @Override
    public AuthResponse register(UserDto userDto) throws UserException {

        User user = userRepo.findByEmail(userDto.getEmail());

        if(user != null) {
            throw new UserException("User already exists with email: " + userDto.getEmail());
        }

        User newUser = new User();

        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());

        User savedUser = userRepo.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setMessage("User registered successfully");
        authResponse.setUser(UserMapper.toDto(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(String email, String password) throws UserException {


        Authentication authentication = authentication(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role =  authorities.iterator().next().getAuthority();
        String token = jwtProvider.generateToken(authentication);

        User user = userRepo.findByEmail(email);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMessage("User logged in successfully");
        authResponse.setUser(UserMapper.toDto(user));
        return authResponse;
    }

    @Override
    public void forgotPassword(String email) throws Exception {

        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UserException("User not found with email: " + email);
        }

        UUID uuid = UUID.randomUUID();

        Long id = (long) uuid.hashCode();

        Otp otp = new Otp();
        otp.setUserId(user.getId());
        otp.setOtp(id);

        emailService.sendResetPasswordEmail(email, id, user.getId());
        otpRepo.save(otp);
    }

    @Override
    public void resetPassword(Long userId, Long otp, String password) throws UserException {

        Otp otpObj = otpRepo.findByUserId(userId);

        if (otpObj == null) {
            throw new UserException("User not found with id: " + userId);
        }

        if(otpObj.getOtp() != otp) {
            throw new UserException("Wrong OTP");
        }

        User user = userRepo.findById(userId).orElse(null);
        if(user == null) {
            throw new UserException("User not found with id: " + userId);
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        otpRepo.delete(otpObj);
    }

    public Authentication authentication(String email, String password) throws UserException {

        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if(userDetails == null) {
            throw new UserException("email id doesn't exist "+ email);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Wrong Password ");
        }
        return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
    }
}
