package com.ben.Access.Service.controller;


import com.ben.Access.Service.exception.UserException;
import com.ben.Access.Service.model.dto.UserDto;
import com.ben.Access.Service.model.response.AuthResponse;
import com.ben.Access.Service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(authService.register(userDto));
    }

    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<AuthResponse> login(
            @PathVariable String email,
            @PathVariable String password) throws UserException {
        return ResponseEntity.ok(authService.login(email, password));
    }

    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email) throws Exception {
        authService.forgotPassword(email);
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/reset-password/{userId}/{otp}")
    public ResponseEntity<String> resetPassword(
            @PathVariable Long userId,
            @PathVariable Long otp,
            @RequestBody String password) throws Exception {
        authService.resetPassword(userId, otp, password);
        return ResponseEntity.ok("Password reset successfully");
    }
}
