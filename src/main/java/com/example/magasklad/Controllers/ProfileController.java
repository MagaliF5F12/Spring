package com.example.magasklad.Controllers;


import com.example.magasklad.Models.Profile;
import com.example.magasklad.Models.Roles;
import com.example.magasklad.Service.ProfileService;
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
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    public ProfileService profileService;


    @GetMapping("/all")
    public String getAllUsers(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "login", required = false) String login,
                              @RequestParam(name = "password", required = false) String password) {
        List<Profile> roles = profileService.findAll();
        System.out.println("количество профилей: " + roles.size());
        model.addAttribute("profilesList", roles);
        model.addAttribute("profile", new Profile());
        return "profiles";

    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("profile") Profile profile, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Profile> roles = profileService.findAll();
            System.out.println("количество студентов: " + roles.size());
            model.addAttribute("profileList", roles);
            model.addAttribute("profile", new Roles());
        }
        profileService.add(profile);
        return "redirect:/profiles/all";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("profile") Profile profile, BindingResult result) {
        profileService.edit(profile.getId(), profile);
        return "redirect:/profiles/all";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody ArrayList<UUID> ids) {
        for (UUID id : ids) {
            profileService.delete(id);
        }
        return "redirect:/profiles/all";

    }

    @GetMapping("/all/{id}")
    public String getIdStudent(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("profileById", profileService.findById(id));
        model.addAttribute("profile", new Profile());
        return "profiles";
    }
}