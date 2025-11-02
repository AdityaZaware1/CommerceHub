package com.ben.Store.Service.external;

import com.ben.Store.Service.external.resillence4j.UserFallBack;
import com.ben.Store.Service.model.dto.UserDto;
import com.ben.Store.Service.model.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Access-Service",
        url = "${auth.service.url}",
        fallback = UserFallBack.class)
public interface AuthService {

    @PostMapping("/register")
    public AuthResponse register(@RequestBody UserDto userDto);
}
