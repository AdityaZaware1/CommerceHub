package com.ben.Access.Service.config;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConfi {

    private JavaMailSender javaMailSender;

    public EmailConfi(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendOtp(String email, String otp) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(
                mimeMessage, "utf-8"
        );

        String subject = "Verification Code";
        String text = "Your verification code is: " + otp;

        messageHelper.setSubject(subject);
        messageHelper.setText(text, true);
        messageHelper.setTo(email);

        try {
            javaMailSender.send(mimeMessage);
        }
        catch (Exception e) {
            throw new Exception("Error while sending email");
        }
    }
}
