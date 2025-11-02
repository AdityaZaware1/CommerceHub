package com.ben.Access.Service.service;

public interface EmailService {

    void sendVerificationOtpEmail(String to, String subject, String body) throws Exception;

    void sendResetPasswordEmail(String to, Long otp, Long userId) throws Exception;


}
