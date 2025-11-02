package com.ben.Store.Service.model.response;

import com.ben.Store.Service.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    public String token;
    public String message;

    private UserDto user;
}
