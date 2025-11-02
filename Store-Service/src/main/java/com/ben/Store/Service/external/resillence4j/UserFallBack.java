package com.ben.Store.Service.external.resillence4j;

import com.ben.Store.Service.external.UserService;
import com.ben.Store.Service.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserService {
    @Override
    public UserDto getUserById(Long id) {
        return new UserDto();
    }
}
