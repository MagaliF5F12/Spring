package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Pagination;
import com.example.magasklad.Models.User;
import com.example.magasklad.Repository.*;
import com.example.magasklad.Service.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String home(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "firstname", required = false) String firstName,
                       @RequestParam(name = "surname", required = false) String surname,
                       @RequestParam(name = "lastname", required = false) String lastname,
                       @RequestParam(name = "role", required = false) String role) {
        System.out.println("firstName: " + firstName);
        Pagination<User> users = userService.getAll(page, firstName, surname, lastname, role);
        System.out.println("количество пользователей: " + users.getCurrentItems().size());
        model.addAttribute("pagination_users", users);
        model.addAttribute("categories", userService.GetAllCategory());
        return "users";

    }

    @PostMapping("/users")
    public String addUser(@RequestParam("firstname") String firstname,
                          @RequestParam("surname") String surname,
                          @RequestParam("lastname") String lastname,
                          @RequestParam("role") String role,
                          Model model) {
        System.out.println(firstname);
        userService.addUser(new User(firstname, surname, lastname, role));
        return "redirect:/users";
    }

    @PostMapping("users/update")
    public String updateStudent(@RequestParam("id") int id,
                                @RequestParam("firstname") String firstname,
                                @RequestParam("surname") String surname,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("role") String role) {
        User user = new User(id, firstname, surname, lastname, role);
        userService.editUser(user);
        return "redirect:/users";
    }

    @PostMapping("users/delete")
    public String deleteUsers(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            userService.deleteUser(id);
        }
        return "redirect:/users";
    }

}

