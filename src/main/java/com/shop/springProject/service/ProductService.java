package com.shop.springProject.service;

import com.shop.springProject.bean.Product;
import com.shop.springProject.repo.ProductRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    private static final String FOLDER_PATH = "C:/images/";
    @Autowired
    private ProductRepository repo;

    public ProductService() {
    }

    public String addProduct(String name, String description,String category, MultipartFile file, int price) throws IOException {
        File directory = new File("C:/images/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File destinationFile = new File(directory, file.getOriginalFilename());
        file.transferTo(destinationFile);
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setImagePath(file.getOriginalFilename());
        product.setPrice(price);
        this.repo.save(product);
        return "Product uploaded successfully";
    }

    public List<Product> getAllProducts() {
        return this.repo.findAll();
    }

    public byte[] getProductImage(String name) throws IOException {
        File imageFile = new File("C:/images/" + name);
        if (!imageFile.exists()) {
            throw new IOException("image is not found in the location");
        } else {
            return Files.readAllBytes(imageFile.toPath());
        }
    }
}
