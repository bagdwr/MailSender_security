package com.example.MailSender.Controller;

import com.example.MailSender.Entity.User;
import com.example.MailSender.Services.MailService;
import com.example.MailSender.Services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    private String mail_code="";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/")
    public String showLogin(){
        return "login.html";
    }

    @GetMapping(value = "/registration")
    public String showRegisterPage(){
        return "registration.html";
    }

    @PostMapping(value = "/createUser")
    public String createUser(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email")String email,
            @RequestParam(value = "password")String password
    ){
            User user=new User(null,name,email,password);
            userService.createUser(user);
            return "redirect:/";
    }

    @GetMapping(value = "/errorPage")
    public String showErrorPage(){
        return "error.html";
    }

    @GetMapping(value = "/profile")
    public String showProfile(){
        return "profile.html";
    }

    @PostMapping(value = "/login")
    public String loginAct(
            @RequestParam(name = "email")String email,
            @RequestParam(name = "password")String password
    ){
        User user=userService.findByEmail(email);
        if (passwordEncoder.matches(password,user.getPassword()) && user!=null){
            mail_code=RandomStringUtils.randomAlphanumeric(20);
            mailService.sendMail(email,mail_code);
            return "redirect:/loginWithRandom?email="+email;
        }else {
            return "redirect:/";
        }

    }
    @GetMapping(value = "/loginWithRandom")
    public String showloginWithRandom(@RequestParam(value = "email")String email
    , Model model){
        model.addAttribute("email",email);
        return "loginWithRandom.html";
    }

    @PostMapping(value = "/loginNumb")
    public String loginNumbAct(
            @RequestParam(value = "email")String email,
            @RequestParam(value = "code")String code
            ){
        if (code.equals(mail_code)){
            mail_code=null;
            return "redirect:/profile";
        }
        return "redirect:/errorPage";
    }
}
