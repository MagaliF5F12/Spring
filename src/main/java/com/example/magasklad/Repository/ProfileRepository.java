package com.example.magasklad.Repository;


import com.example.magasklad.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Profile findByUsername(String login);

    boolean existsByUsername(String login);

}

