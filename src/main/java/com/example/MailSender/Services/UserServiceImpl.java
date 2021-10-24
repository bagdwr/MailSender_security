package com.example.MailSender.Services;

import com.example.MailSender.Entity.User;
import com.example.MailSender.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(s);
        if (user!=null){
            org.springframework.security.core.userdetails.User secUser=new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),null);
            return secUser;
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        User createUser=userRepository.findByEmail(user.getEmail());
        if (createUser==null){
            return userRepository.save(user);
        }
        return null;
    }
}
