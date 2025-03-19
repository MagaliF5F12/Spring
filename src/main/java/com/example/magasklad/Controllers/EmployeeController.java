package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Employee;
import com.example.magasklad.Service.BaseService;
import com.example.magasklad.Service.FactoryService;
import com.example.magasklad.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController<Employee, UUID> {

    @Autowired
    UserService userService;

    @Autowired
    FactoryService factoryService;

    public EmployeeController(BaseService<Employee, UUID> baseService) {
        super(baseService,
                Employee.class,
                "employee",
                new LinkedHashMap<>(){{
                    put("Выход", "/logout");
                    put("Список продуктов", "/products/all");
                    put("Список категорий", "/category/all");
                    put("Список дефектов", "/defects/all");
                    put("Список заводов", "/employee/all");
                }});
    }

    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> sublist = Map.of(
                "users", userService.findAll(),
                "factory", factoryService.findAll()
        );
        model.addAttribute("sublist", sublist);
        return super.getAll(model, page);
    }
}
