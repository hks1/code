package com.example.hplus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {
    @GetMapping("/redirectToLinkedIn")
    public String redirectOut(){
        System.out.println("in redirect controller");
        return "redirect:http://www.linkedin.com";
    }
}
