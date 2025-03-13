package com.example.magasklad.Service;

import com.example.magasklad.Models.*;
import com.example.magasklad.Repository.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class StudentService extends BaseService<Student, Long>{
    @Autowired
    public StudentService(JpaRepository<Student, Long> repository) {
        super(repository);
    }
}