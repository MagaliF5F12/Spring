package com.example.magasklad.Repository;

import com.example.magasklad.Models.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    AtomicInteger idCounter = new AtomicInteger(1);
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getUsers() {
        return students.stream().filter(user -> !user.getIsDeleted()).collect(Collectors.toCollection(ArrayList::new));
    }

    public Student getUserById(int id) {
        return students.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public boolean addUser(Student student) {
        student.setId(idCounter.getAndIncrement());
        students.add(student);
        return true;
    }

    public boolean editUser(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setIsDeleted(true);
                return true;
            }
        }
        return false;
    }
}
