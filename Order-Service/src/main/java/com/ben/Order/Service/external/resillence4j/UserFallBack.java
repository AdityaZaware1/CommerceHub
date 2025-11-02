package com.ben.Order.Service.external.resillence4j;

import com.ben.Order.Service.external.UserService;
import com.ben.Order.Service.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserService {

    @Override
    public UserDto getUserById(Long id) {
        return new UserDto();
    }
}
