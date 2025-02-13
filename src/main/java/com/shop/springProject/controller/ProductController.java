package com.shop.springProject.controller;

import com.shop.springProject.bean.Product;
import com.shop.springProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;


    @PostMapping("/add")               //from the request hitting this controller get the data on the identifiers
    public ResponseEntity<String> addProduct(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("category") String category,
                                             @RequestParam("image")MultipartFile file, @RequestParam("price") int price) throws IOException {
        String response= service.addProduct(name,description,category,file,price);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllproducts(){
        List<Product> products=service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }


    @GetMapping("/image/{name}")
    public ResponseEntity<byte[]> getproductbyname(@PathVariable String name) throws IOException {
        byte[] imagedata=service.getProductImage(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(imagedata);
    }




}
