package com.ben.Access.Service.repo;

import com.ben.Access.Service.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepo extends JpaRepository<Otp, Long> {
    Otp findByUserId(Long userId);
}
