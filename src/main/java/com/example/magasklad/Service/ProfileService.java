package com.example.magasklad.Service;


import com.example.magasklad.Models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService extends BaseService<Profile, UUID> {
    @Autowired
    public ProfileService(JpaRepository<Profile, UUID> repository) {
        super(repository);
    }
}
