package com.shop.springProject.controller;

import com.shop.springProject.bean.Product;
import com.shop.springProject.service.ProductService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/products"})
public class ProductController {
    @Autowired
    private ProductService service;

    public ProductController() {
    }

    @PostMapping({"/add"})
    public ResponseEntity<String> addProduct(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("category") String category,@RequestParam("image") MultipartFile file, @RequestParam("price") int price) throws IOException {
        String response = this.service.addProduct(name, description, category,file,price);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<Product>> getAllproducts() {
        List<Product> products = this.service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping({"/image/{name}"})
    public ResponseEntity<byte[]> getproductbyname(@PathVariable String name) throws IOException {
        byte[] imagedata = this.service.getProductImage(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(imagedata);
    }
}

