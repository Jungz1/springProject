package com.shop.springProject.repo;

import com.shop.springProject.bean.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
