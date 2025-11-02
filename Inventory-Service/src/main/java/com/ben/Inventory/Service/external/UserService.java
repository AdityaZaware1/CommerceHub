package com.ben.Inventory.Service.external;

import com.ben.Inventory.Service.external.resillence4j.UserFallBack;
import com.ben.Inventory.Service.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-Service", url = "${user.service.url}", fallback = UserFallBack.class)
public interface UserService {

    @GetMapping("/")
    public UserDto getCurrentUser();

    @GetMapping("/id/{id}")
    public UserDto getUserById(@PathVariable Long id);

}
