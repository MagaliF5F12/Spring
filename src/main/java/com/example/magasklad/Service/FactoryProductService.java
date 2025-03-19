package com.example.magasklad.Service;

import com.example.magasklad.Models.FactoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FactoryProductService extends BaseService<FactoryProduct, UUID> {
    public FactoryProductService(JpaRepository<FactoryProduct, UUID> repository) {
        super(repository);
    }
}
