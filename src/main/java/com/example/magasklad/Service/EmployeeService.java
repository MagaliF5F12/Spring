package com.example.magasklad.Service;

import com.example.magasklad.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService extends BaseService<Employee, UUID> {
    public EmployeeService(JpaRepository<Employee, UUID> repository) {
        super(repository);
    }
}
