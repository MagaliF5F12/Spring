package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Pagination;
import com.example.magasklad.Models.Users;
import com.example.magasklad.Service.BaseService;
import com.example.magasklad.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController<Users, UUID> {

    @Autowired
    private ProfileService profileService;

    public UsersController(BaseService<Users, UUID> baseService) {
        super(baseService, Users.class,
                "users",
                new LinkedHashMap<>(){
                    {
                        put("Выход", "/logout");
                        put("Список профилей", "/profiles/all");
                    }}
        );
    }

    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> sublist = Map.of(
                "profile", profileService.findAll()
        );
        model.addAttribute("sublist", sublist);
        return super.getAll(model, page);
    }
}
