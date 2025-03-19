package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Factory;
import com.example.magasklad.Service.BaseService;
import com.example.magasklad.Service.ProductService;
import com.example.magasklad.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/factory")
public class FactoryController extends BaseController<Factory, UUID> {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    public FactoryController(BaseService<Factory, UUID> baseService) {
        super(baseService,
                Factory.class,
                "factory",
                new LinkedHashMap<>(){{
                    put("Выход", "/logout");
                    put("Список продуктов", "/products/all");
                    put("Список категорий", "/category/all");
                    put("Список дефектов", "/defects/all");
                    put("Список Сотрудников", "/employee/all");
                }});
    }

    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> sublist = Map.of(
                "employees", userService.findAll(),
                "products", productService.findAll()
        );
        model.addAttribute("sublist", sublist);
        return super.getAll(model, page);
    }
}
