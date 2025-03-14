package com.example.magasklad.Repository;

import com.example.magasklad.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface RolesRepository extends JpaRepository<Roles, UUID> {
}