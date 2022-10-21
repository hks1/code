package com.example.hplus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("ending user session");
        System.out.println(session.getAttribute("login"));
        session.invalidate();
        // will not work as session already invalidated
        //System.out.println(session.getAttribute("login"));
        return "login";
    }
}
