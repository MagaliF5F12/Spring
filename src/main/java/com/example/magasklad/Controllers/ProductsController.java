package com.example.magasklad.Controllers;

import com.example.magasklad.Models.FactoryProduct;
import com.example.magasklad.Models.Product;
import com.example.magasklad.Service.BaseService;
import com.example.magasklad.Service.CategoryService;
import com.example.magasklad.Service.FactoryService;
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
@RequestMapping("/products")
public class ProductsController extends BaseController<Product, UUID>{

    @Autowired
    FactoryService factoryService;

    @Autowired
    CategoryService categoryService;



    public ProductsController(BaseService<Product, UUID> baseService) {
        super(baseService,
                Product.class,
                "products",
                new LinkedHashMap<>(){
                    {
                        put("Выход", "/logout");
                        put("Список дефектов", "/defects/all");
                        put("Список категорий", "/category/all");
                        put("Список заводов", "/factory/all");
                        put("Список Сотрудников", "/employee/all");
                    }}
        );
    }

    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> sublist = Map.of(
                "factories", factoryService.findAll(),
                "category", categoryService.findAll()
        );
        model.addAttribute("sublist", sublist);
        return super.getAll(model, page);
    }

}
