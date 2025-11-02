package com.ben.Access.Service.service;

import com.ben.Access.Service.exception.UserException;
import com.ben.Access.Service.model.dto.UserDto;
import com.ben.Access.Service.model.response.AuthResponse;

public interface AuthService {

    AuthResponse register(UserDto userDto) throws UserException;

    AuthResponse login(String email, String password) throws UserException;

    void forgotPassword(String email) throws Exception;

    void resetPassword(Long userId, Long otp, String password) throws UserException;
}
