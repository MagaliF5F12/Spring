package com.example.magasklad.Service;

import com.example.magasklad.Models.Defects;
import com.example.magasklad.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefectsService extends BaseService<Defects, UUID> {
    @Autowired
    public DefectsService(JpaRepository<Defects, UUID> repository) {
        super(repository);
    }
}
