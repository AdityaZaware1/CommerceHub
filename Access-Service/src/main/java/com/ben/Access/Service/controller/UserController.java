package com.ben.Access.Service.controller;

import com.ben.Access.Service.entity.SubScription;
import com.ben.Access.Service.exception.UserException;
import com.ben.Access.Service.model.dto.UserDto;
import com.ben.Access.Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(
            @RequestHeader("Authorization") String token
    ) throws UserException {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) throws UserException {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws UserException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getCurrentUser() throws UserException {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SubScription> getSubScription(@PathVariable Long id) {
        return ResponseEntity.ok(new SubScription());
    }

    @GetMapping("/getAll")
    public ResponseEntity<SubScription> getAllSubScription() {
        return ResponseEntity.ok(new SubScription());
    }
}
