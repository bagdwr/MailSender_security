package com.example.MailSender.Services;

import com.example.MailSender.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);
    User getById(Long id);
    User findByEmail(String email);
    User createUser(User user);
}
