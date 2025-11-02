package com.ben.Branch.Service.external;

import com.ben.Branch.Service.external.resillence4j.UserFallBack;
import com.ben.Branch.Service.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "User-Service",
        url = "${user.service.url}",
        fallback = UserFallBack.class)
public interface UserService {

    @GetMapping("/")
    public UserDto getCurrentUser();

}
