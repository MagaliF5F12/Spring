package com.example.magasklad.Repository;

import com.example.magasklad.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
}
