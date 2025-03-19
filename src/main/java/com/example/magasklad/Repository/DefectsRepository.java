package com.example.magasklad.Repository;

import com.example.magasklad.Models.Defects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DefectsRepository extends JpaRepository<Defects, UUID> {
}
