package com.ben.Payment.Service.external;

import com.ben.Payment.Service.dto.UserDto;

public class UserFallBack implements UserService{
    @Override
    public UserDto getUserById(Long id) {
        return new UserDto();
    }
}
