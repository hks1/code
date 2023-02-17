package com.hks.bcryptservicedemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUser(@PathVariable("id") Long id){
        return userRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody User user){
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@Valid @RequestBody User user){
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());

        if(!dbUser.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        String hash = dbUser.get().getPassword();

        if(!BCrypt.checkpw(user.getPassword(), hash)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("wrong password!");
        }

        return ResponseEntity.ok("Welcome " + dbUser.get().getEmail());
    }

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


}
