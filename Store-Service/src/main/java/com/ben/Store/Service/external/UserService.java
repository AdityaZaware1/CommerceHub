package com.ben.Store.Service.external;

import com.ben.Store.Service.external.resillence4j.UserFallBack;
import com.ben.Store.Service.model.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-Service",
        url = "${user.service.url}",
        fallback = UserFallBack.class)
public interface UserService {

    @GetMapping("/id/{id}")
    public UserDto getUserById(@PathVariable Long id);


}
