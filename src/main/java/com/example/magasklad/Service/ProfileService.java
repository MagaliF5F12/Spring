package com.example.magasklad.Service;


import com.example.magasklad.Models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.magasklad.config.PasswordEncoderConfig;

import java.util.UUID;

@Service
public class ProfileService extends BaseService<Profile, UUID> {
    @Autowired
    public ProfileService(JpaRepository<Profile, UUID> repository) {
        super(repository);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Profile add(Profile entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.add(entity);
    }

    @Override
    public Profile edit(UUID id, Profile entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.edit(id, entity);
    }
}
