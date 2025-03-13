package com.example.magasklad.Controllers;


import com.example.magasklad.Models.Student;
import com.example.magasklad.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    public StudentService studentService;


    @GetMapping("/all")
    public String getAllUsers(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "FIO", required = false) String FIO,
                              @RequestParam(name = "group", required = false) String group,
                              @RequestParam(name = "course", required = false) String course) {
        List<Student> students = studentService.findAll();
        System.out.println("количество студентов: " + students.size());
        model.addAttribute("pagination_students", students);
        model.addAttribute("student", new Student());

        return "students";

    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Student> students = studentService.findAll();
            System.out.println("количество студентов: " + students.size());
            model.addAttribute("pagination_users", students);
            model.addAttribute("student", new Student());
        }
        studentService.add(student);
        return "redirect:/students/all";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        studentService.edit(student.getId(), student);
        return "redirect:/students/all";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody ArrayList<UUID> ids) {
        for(UUID id : ids) {
            studentService.delete(id);
        }
        return "redirect:/students/all";

    }
    @GetMapping("/all/{id}")
    public String getIdStudent(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("students", studentService.findById(id));
        model.addAttribute("student", new Student());
        return "students";
    }

}
