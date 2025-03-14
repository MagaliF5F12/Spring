package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Profile;
import com.example.magasklad.Models.RoleEnum;
import com.example.magasklad.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegisterController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/reg")
    public String registrationView(Model model) {
        model.addAttribute("profile", new Profile());

        return "reg";
    }

    @PostMapping("/reg")
    public String registrationUser(Profile user, Model model){
        if(profileRepository.existsByUsername(user.getUsername())){
            model.addAttribute("message", "Пользователь с таким именем уже есть");
            return "reg";
        }
        if(user.getUsername().length() < 3){
            model.addAttribute("message", "Имя пользователя должно быть не менее 3 символов");
            return "reg";
        }
        if(user.getPassword().length() < 8){
            model.addAttribute("message", "Пароль должен быть не менее 8 символов");
            return "reg";
        }
        if(!validation(user.getPassword())){
            model.addAttribute("message", "Слабый пароль");
            return "reg";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        profileRepository.save(user);

        return "redirect:/login";
    }
    private boolean validation(String password) {
        int result = 0;
        if (password.matches(".*[A-Z].*") || password.matches(".*[А-Я].*"))
            result++;
        if (password.matches(".*[a-z].*") || password.matches(".*[а-я].*"))
            result++;
        if (password.matches(".*\\d.*"))
            result++;
        if (password.matches(".*[!@#$%^&*()\\-_=+{};:,<.>].*"))
            result++;
        return result >= 2;
    }


}