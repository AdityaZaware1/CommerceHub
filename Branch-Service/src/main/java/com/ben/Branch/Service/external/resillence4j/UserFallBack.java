package com.ben.Branch.Service.external.resillence4j;

import com.ben.Branch.Service.external.UserService;
import com.ben.Branch.Service.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserService {
    @Override
    public UserDto getCurrentUser() {
        return new UserDto();
    }
}
