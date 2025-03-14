package com.example.magasklad.Controllers.API;

import com.example.magasklad.Models.Student;
import com.example.magasklad.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class APIStudents extends BaseAPI<Student, UUID> {
    @Autowired
    protected APIStudents(BaseService<Student, UUID> baseService) {
        super(baseService);
    }
}
