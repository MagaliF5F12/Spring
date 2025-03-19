package com.example.magasklad.Repository;

import com.example.magasklad.Models.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, UUID> {
}
