package com.ben.Access.Service.service.impl;

import com.ben.Access.Service.entity.SubScription;
import com.ben.Access.Service.enums.UserRole;
import com.ben.Access.Service.model.dto.UserDto;
import com.ben.Access.Service.repo.SubScriptionRepo;
import com.ben.Access.Service.service.SubScriptionService;
import com.ben.Access.Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubScriptionServiceImpl implements SubScriptionService {

    private final SubScriptionRepo subScriptionRepo;
    private final UserService userService;

    @Override
    public SubScription createSubScription(SubScription subScription) {

        UserDto userDto = userService.getCurrentUser();

        if (!userDto.getRole().equals(UserRole.ADMIN)){
            throw new RuntimeException("User is not an admin");
        }

        return subScriptionRepo.save(subScription);
    }

    @Override
    public SubScription getSubScription(Long id) {
        return subScriptionRepo.findById(id).orElse(null);
    }

    @Override
    public List<SubScription> getAllSubScription() {
        return subScriptionRepo.findAll();
    }
}
