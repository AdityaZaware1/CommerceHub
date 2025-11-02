package com.ben.Inventory.Service.external.resillence4j;

import com.ben.Inventory.Service.external.UserService;
import com.ben.Inventory.Service.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserService {
    @Override
    public UserDto getCurrentUser() {
        return new UserDto();
    }

    @Override
    public UserDto getUserById(Long id) {
        return new UserDto();
    }
}
