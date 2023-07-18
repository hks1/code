package com.example.hplus.controllers;

import com.example.hplus.beans.Login;
import com.example.hplus.beans.User;
import com.example.hplus.exceptions.ApplicationException;
import com.example.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // not needed because of DefaultModelAttributeController advice
    /*@ModelAttribute("login")
    public Login getDefaultLogin(){
        return new Login();
    }*/

    @PostMapping("/login")
    public String login(@ModelAttribute("login")Login login, HttpSession session){
        // full session management with session object
        //session.setAttribute("", "");
        //session.setMaxInactiveInterval(1);
        User user = userRepository.findByName(login.getUsername());
        if(user == null) {
            throw new ApplicationException("User not found.");
        }
        return "forward:/userprofile";
    }

    /*@ExceptionHandler(ApplicationException.class)
    public String handleException(){
        System.out.println("in exception handler of Login Controller");
        return "error";
    }*/
}