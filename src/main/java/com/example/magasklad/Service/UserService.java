package com.example.magasklad.Service;

import com.example.magasklad.Models.Pagination;
import com.example.magasklad.Models.Users;
import com.example.magasklad.Repository.UserRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<Users, UUID> {
    @Autowired
    public UserService(JpaRepository<Users, UUID> repository) {
        super(repository);
    }
}