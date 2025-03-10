package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Pagination;
import com.example.magasklad.Models.Student;
import com.example.magasklad.Models.User;
import com.example.magasklad.Service.StudentService;
import com.example.magasklad.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService userService;

    @GetMapping("/students")
    public String home(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        Pagination<Student> users = userService.getAll(page);
        System.out.println("количество пользователей: " + users.getCurrentItems().size());
        model.addAttribute("pagination_users", users);
        model.addAttribute("categories", userService.GetAllCategory());
        return "students";

    }

    @PostMapping("/students")
    public String addUser(@RequestParam("FIO") String FIO,
                          @RequestParam("group") String group,
                          @RequestParam("course") int course,
                          Model model) {
        userService.addUser(new Student(FIO, group, course));
        return "redirect:/students";
    }

    @PostMapping("students/update")
    public String updateStudent(@RequestParam("id") int id,
                                @RequestParam("FIO") String FIO,
                                @RequestParam("group") String group,
                                @RequestParam("course") int course) {
        Student user = new Student(id, FIO, group, course);
        userService.editUser(user);
        return "redirect:/students";
    }

    @PostMapping("students/delete")
    public String deleteUsers(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            userService.deleteUser(id);
        }
        return "redirect:/students";
    }
}
