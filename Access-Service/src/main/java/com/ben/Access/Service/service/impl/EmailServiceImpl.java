package com.ben.Access.Service.service.impl;

import com.ben.Access.Service.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendVerificationOtpEmail(String to, String subject, String body) throws Exception {

    }

    @Override
    public void sendResetPasswordEmail(String to, Long otp, Long userId) throws Exception {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject("Reset Password");
            helper.setText("Your verification code is: " + otp, true);
            javaMailSender.send(mimeMessage);
        }
        catch (MailException e) {
            throw new Exception("falied to send email", e);
        }
    }
}
