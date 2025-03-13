package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Pagination;
import com.example.magasklad.Models.Users;
import com.example.magasklad.Repository.*;
import com.example.magasklad.Service.*;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/users")
public class MainController {
    @Autowired
    public UserService userService;


    @GetMapping("/all")
    public String getAllUsers(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "firstname", required = false) String firstName,
                              @RequestParam(name = "surname", required = false) String surname,
                              @RequestParam(name = "lastname", required = false) String lastname,
                              @RequestParam(name = "role", required = false) String role) {
        List<Users> users = userService.findAll();
        System.out.println("количество пользователей: " + users.size());
        model.addAttribute("pagination_users", users);
        model.addAttribute("user", new Users());

        return "users";

    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("users") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Users> users = userService.findAll();
            model.addAttribute("pagination_users", users);
            model.addAttribute("users", users);
            return "users";
        }
        userService.add(user);
        return "redirect:/users/all";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("users") Users user, BindingResult result) {
        userService.edit(user.getId(), user);
        return "redirect:/users/all";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody ArrayList<Long> ids) {
        for (Long id : ids) {
            userService.delete(id);
        }
        return "redirect:/users/all";

    }

    @GetMapping("/all/{id}")
    public String getIdStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.findById(id));
        model.addAttribute("user", new Users());
        return "users";
    }


}



