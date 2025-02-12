package com.shop.springProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.shop.springProject.bean.User;
import com.shop.springProject.repo.UserRepository;

@RestController
@RequestMapping("/login")
public class UserController {

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

}

