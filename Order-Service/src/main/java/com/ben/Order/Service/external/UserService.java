package com.ben.Order.Service.external;

import com.ben.Order.Service.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Store-Service", url = "${user.service.url}")
public interface UserService {

    @GetMapping("/id/{id}")
    public UserDto getUserById(@PathVariable Long id);
}
