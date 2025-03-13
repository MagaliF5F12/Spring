package com.example.magasklad.Controllers;


import com.example.magasklad.Models.Roles;
import com.example.magasklad.Models.Student;
import com.example.magasklad.Service.RolesService;
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
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    public RolesService rolesService;


    @GetMapping("/all")
    public String getAllUsers(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "title", required = false) String title) {
        List<Roles> roles = rolesService.findAll();
        System.out.println("количество студентов: " + roles.size());
        model.addAttribute("rolesList", roles);
        model.addAttribute("role", new Roles());

        return "roles";

    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("roles") Roles role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Roles> roles = rolesService.findAll();
            System.out.println("количество студентов: " + roles.size());
            model.addAttribute("rolesList", roles);
            model.addAttribute("role", new Roles());
        }
        rolesService.add(role);
        return "redirect:/roles/all";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("roles") Roles role, BindingResult result) {
        rolesService.edit(role.getId(), role);
        return "redirect:/roles/all";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody ArrayList<UUID> ids) {
        for(UUID id : ids) {
            rolesService.delete(id);
        }
        return "redirect:/roles/all";

    }
    @GetMapping("/all/{id}")
    public String getIdStudent(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("rolesById", rolesService.findById(id));
        model.addAttribute("role", new Student());
        return "roles";
    }
}

