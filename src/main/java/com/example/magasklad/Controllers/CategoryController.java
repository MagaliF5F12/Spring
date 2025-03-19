package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Category;
import com.example.magasklad.Service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController<Category, UUID> {

    public CategoryController(BaseService<Category, UUID> baseService) {
        super(baseService, Category.class,
                "category",
                new LinkedHashMap<>() {
                    {
                        put("Выход", "/logout");
                        put("Список продуктов", "/products/all");
                        put("Список дефектов", "/category/all");
                        put("Список заводов", "/factory/all");
                        put("Список Сотрудников", "/employee/all");
                    }
                }
        );
    }
}
