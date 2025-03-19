package com.example.magasklad.Service;

import com.example.magasklad.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService extends BaseService<Category, UUID> {
    @Autowired
    public CategoryService(JpaRepository<Category, UUID> repository) {
        super(repository);
    }
}
