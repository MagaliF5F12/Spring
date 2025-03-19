package com.example.magasklad.Controllers;

import com.example.magasklad.Models.*;
import com.example.magasklad.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/fproducts")
public class FactoryProductController extends BaseController<FactoryProduct, UUID> {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    public FactoryProductController(BaseService<FactoryProduct, UUID> baseService) {
        super(baseService, FactoryProduct.class,
                "fproducts",
                new LinkedHashMap<>(){{
                    put("Выход", "/logout");
                }});
    }


    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> sublist = Map.of(
                "users", userService.findAll().stream()
                        .filter(x -> x.getId().equals(getCurrentUserId())) // Используем equals() вместо ==
                        .toList(),
                "product", productService.findAll()
        );
        List<FactoryProduct> filteredList = baseService.findAll().stream()
                .filter(x -> x.getUsers().getId().equals(getCurrentUserId()))
                .toList();

        ArrayList<FactoryProduct> tmp = new ArrayList<>(filteredList);
        Pagination<FactoryProduct> list = new Pagination<>(tmp, page);
        var instance = cls.newInstance();
        ArrayList<String> columns = (ArrayList<String>) instance.getClass().getMethod("getColumns").invoke(instance);
        model.addAttribute("sublist", sublist);
        model.addAttribute("list", list);
        model.addAttribute("title_model", title_model);
        model.addAttribute("urls", urls);
        model.addAttribute("page", page);
        model.addAttribute("columns", columns);
        model.addAttribute("titleSite", "Все записи модели " + title_model);
        model.addAttribute("list", list);
        model.addAttribute("title_list", title_list);
        model.addAttribute("newobject", instance);
        return "base";
    }
    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            return (UserDetails) principal;
        }
        return null;
    }
    public  UUID getCurrentUserId() {
        String currentUsername = getCurrentUser().getUsername();
        for (Profile profile : profileService.findAll())
            if (profile.getUsername().equals(currentUsername))
                return profile.getUsers().getId();
        return null;
    }
}
