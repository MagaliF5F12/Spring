package com.example.magasklad.Controllers;

import com.example.magasklad.Models.*;
import com.example.magasklad.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/defects")
public class DefectsController extends BaseController<Defects, UUID> {

    @Autowired
    private UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FactoryProductService factoryProductService;

    public DefectsController(BaseService<Defects, UUID> baseService) {
        super(baseService,
                Defects.class,
                "defects",
                new LinkedHashMap<>(){
                    {
                        put("Выход", "/logout");
                        put("Список продуктов", "/products/all");
                        put("Список категорий", "/category/all");
                        put("Список заводов", "/factory/all");
                        put("Список Сотрудников", "/employee/all");
                    }});
    }
    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Set<Users> employeesSet = new HashSet<>(factoryProductService.findAll().stream()
                .map(FactoryProduct::getUsers)
                .collect(Collectors.toSet()));

        List<Users> usersInEmployees = userService.findAll().stream()
                .filter(employeesSet::contains)  // Проверяем, содержится ли пользователь в Set
                .collect(Collectors.toList());
        Set<Product> productSet = new HashSet<>(factoryProductService.findAll().stream()
                .map(FactoryProduct::getProduct)
                .collect(Collectors.toSet()));

        List<Product> productsInProductSet = productService.findAll().stream()
                .filter(productSet::contains)
                .collect(Collectors.toList());
        Map<String, Object> sublist = Map.of(
                "users", usersInEmployees,
                "product", productsInProductSet
        );
        model.addAttribute("sublist", sublist);
        return super.getAll(model, page);
    }
}
