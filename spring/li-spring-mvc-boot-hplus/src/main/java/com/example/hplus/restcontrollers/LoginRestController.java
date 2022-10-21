package com.example.hplus.restcontrollers;

import com.example.hplus.beans.Login;
import com.example.hplus.beans.User;
import com.example.hplus.exceptions.LoginFailureException;
import com.example.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/hplus/rest/loginuser")
    public ResponseEntity loginUser(@RequestBody Login login) throws LoginFailureException{
        System.out.println("login data: " + login);
        User user = userRepository.findByName(login.getUsername());

        if(null == user){
            //return ResponseEntity.status(404).build();
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }

        if(user.getUsername().equals(login.getUsername()) &&
        user.getPassword().equals(login.getPassword())){
            return new ResponseEntity("Welcome, "+user.getUsername(), HttpStatus.OK);
        }else{
            // throw new Exception
            throw new LoginFailureException("Invalid username or password");
        }
    }
}
