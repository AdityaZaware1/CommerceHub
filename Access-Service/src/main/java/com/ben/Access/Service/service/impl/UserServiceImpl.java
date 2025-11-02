package com.ben.Access.Service.service.impl;

import com.ben.Access.Service.entity.User;
import com.ben.Access.Service.exception.UserException;
import com.ben.Access.Service.mapper.UserMapper;
import com.ben.Access.Service.model.dto.UserDto;
import com.ben.Access.Service.repo.UserRepo;
import com.ben.Access.Service.security.JwtProvider;
import com.ben.Access.Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;

    @Override
    public UserDto getUserProfileFromJwt(String jwt) throws UserException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UserException("User not found with email: " + email);
        }
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) throws UserException {

        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UserException("User not found with email: " + email);
        }
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto getUserById(Long id) throws UserException {

        User user = userRepo.findById(id).orElse(null);

        if(user == null) {
            throw new UserException("User not found with id: " + id);
        }
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto getCurrentUser() throws UserException {

        User user = userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user == null) {
            throw new UserException("User not found with email: " + SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return UserMapper.toDto(user);
    }
}
