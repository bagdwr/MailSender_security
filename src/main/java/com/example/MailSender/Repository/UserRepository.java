package com.example.MailSender.Repository;

import com.example.MailSender.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User getById(Long id);
    User findByEmail(String email);
}
