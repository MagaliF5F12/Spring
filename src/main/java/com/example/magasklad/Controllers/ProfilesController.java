package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Profile;
import com.example.magasklad.Service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/profiles")
public class ProfilesController extends BaseController<Profile, UUID> {
    public ProfilesController(BaseService<Profile, UUID> baseService) {
        super(baseService, Profile.class,
                "profiles",
                new LinkedHashMap<>(){
                    {
                        put("Выход", "/logout");
                        put("Список пользователей", "/users/all");
                    }}
        );
    }
}
