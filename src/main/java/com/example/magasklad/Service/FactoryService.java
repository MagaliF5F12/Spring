package com.example.magasklad.Service;

import com.example.magasklad.Models.Factory;
import com.example.magasklad.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FactoryService extends BaseService<Factory, UUID> {
    @Autowired
    public FactoryService(JpaRepository<Factory, UUID> repository) {
        super(repository);
    }
}