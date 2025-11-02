package com.ben.Payment.Service.external;

import com.ben.Payment.Service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Store-Service", url = "${user.service.url}", fallback = UserFallBack.class)
public interface UserService {

    @GetMapping("/id/{id}")
    public UserDto getUserById(@PathVariable Long id);
}
