package com.shop.springProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.springProject.bean.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);
}
