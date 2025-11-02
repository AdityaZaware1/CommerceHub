package com.ben.Access.Service.service;

import com.ben.Access.Service.exception.UserException;
import com.ben.Access.Service.model.dto.UserDto;

public interface UserService {

    UserDto getUserProfileFromJwt(String jwt) throws UserException;
    UserDto getUserByEmail(String email) throws UserException;
    UserDto getUserById(Long id) throws UserException;
    UserDto getCurrentUser() throws  UserException;
}
