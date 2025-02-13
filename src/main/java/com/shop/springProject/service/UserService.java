package com.shop.springProject.service;

import com.shop.springProject.bean.User;
import com.shop.springProject.repo.UserRepository;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public UserService() {
    }

    public String addUser(String username, String password,String userType) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        this.repo.save(user);
        return "User uploaded successfully";
    }

    public List<User> getAllUsers() {
        return this.repo.findAll();
    }
}
