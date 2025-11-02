package com.ben.Access.Service.mapper;

import com.ben.Access.Service.entity.User;
import com.ben.Access.Service.model.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
