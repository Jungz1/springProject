package com.shop.springProject.controller;

import com.shop.springProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.shop.springProject.bean.User;
import com.shop.springProject.repo.UserRepository;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repo;

    @PostMapping("/authenticate")
    public String authernticateUser(@RequestBody User user){
        User founduser= repo.findByUsername(user.getUsername());
        if(founduser!=null && founduser.getPassword().equals(user.getPassword())){
            return "Login Successful";
        }
        return "Invalid Credentials";

    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        RestTemplate temp=new RestTemplate();
        String url="http://localhost:8082/register";
        return temp.postForObject(url,user,String.class);
    }

    @PostMapping({"/add"})
    public ResponseEntity<String> addUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("userType") String userType) throws IOException {
        String response = this.service.addUser(username, password, userType);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<User>> getAllusers() {
        List<User> users = this.service.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}

