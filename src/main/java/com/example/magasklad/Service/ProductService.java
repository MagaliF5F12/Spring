package com.example.magasklad.Service;

import com.example.magasklad.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService extends BaseService<Product, UUID> {
    @Autowired
    public ProductService(JpaRepository<Product, UUID> repository) {
        super(repository);
    }
}
