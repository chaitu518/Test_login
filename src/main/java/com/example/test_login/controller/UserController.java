package com.example.test_login.controller;

import com.example.test_login.dto.UserDTO;
import com.example.test_login.models.User;
import com.example.test_login.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserService userService;
   public UserController(UserService userService) {
       this.userService = userService;
   }
   @PostMapping("/login")
    public String login(@RequestBody User user){
       return userService.login(user.getEmail(),user.getPassword());
   }
   @PostMapping("/register")
    public UserDTO register(@RequestBody User user){
       return userService.register(user.getUsername(), user.getEmail(), user.getPassword());
   }
    @GetMapping("/me")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails user){
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }
}
