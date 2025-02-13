package com.shop.springProject.controller;

import com.shop.springProject.bean.Purchase;
import com.shop.springProject.service.PurchaseService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/purchases"})
public class PurchaseController {
    @Autowired
    private PurchaseService service;

    public PurchaseController() {
    }

    @PostMapping({"/add"})
    public ResponseEntity<String> addPurchase(@RequestParam("product_id") Long product_id, @RequestParam("product_name") String product_name, @RequestParam("customer_id") Long customer_id, @RequestParam("customer_name") String customer_name) throws IOException {
        String response = this.service.addPurchase(product_id, product_name, customer_id,customer_name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<Purchase>> getAllpurchases() {
        List<Purchase> purchases = this.service.getAllPurchases();
        return ResponseEntity.status(HttpStatus.OK).body(purchases);
    }


}
