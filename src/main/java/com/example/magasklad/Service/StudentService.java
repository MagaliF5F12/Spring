package com.example.magasklad.Service;

import com.example.magasklad.Models.*;
import com.example.magasklad.Repository.*;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {this.studentRepository = studentRepository;}

    public Pagination<Student> getAll(int page) {
        ArrayList<Student> users = studentRepository.getUsers();
        return new Pagination<Student>(users, page);
    }
    public Student getUserById(int id) {
        return studentRepository.getUserById(id);
    }

    public boolean addUser(Student user) {
        return studentRepository.addUser(user);
    }

    public boolean editUser(Student user) {
        return studentRepository.editUser(user);
    }

    public boolean deleteUser(int id) {
        return studentRepository.deleteUser(id);
    }

    public HashSet<String> GetAllCategory() {
        HashSet<String> hashSet = new HashSet<>();

        for (Student user : studentRepository.getUsers())
            hashSet.add(user.getFIO());

        return hashSet;
    }
}
