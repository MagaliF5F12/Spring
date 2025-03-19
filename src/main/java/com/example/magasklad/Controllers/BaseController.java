package com.example.magasklad.Controllers;

import com.example.magasklad.Models.Pagination;
import com.example.magasklad.Service.BaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController<T, ID> {

    @Autowired
    public BaseService<T, ID> baseService;

    Class<T> cls;

    String title_list, title_model;
    Map<String, String> urls;

    public BaseController(BaseService<T, ID> baseService, Class<T> cls, String title_list, Map<String, String> urls) {
        this.baseService = baseService;
        this.cls = cls;
        this.title_list = title_list;
        this.title_model = cls.getSimpleName().toLowerCase();
        this.urls = urls;
    }

    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Pagination<T> list = baseService.findAll(page);
        var instance = cls.newInstance();
        ArrayList<String> columns = (ArrayList<String>) instance.getClass().getMethod("getColumns").invoke(instance);
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

    @PostMapping("/add")
    public String create(@Valid T entity, BindingResult result, Model model) throws InstantiationException, IllegalAccessException {
        if (result.hasErrors()) {
            return "redirect:/"+ title_list + "/all";
        }
        baseService.add(entity);
        return "redirect:/"+ title_list + "/all";


    }

    @PostMapping("/update")
    public String edit(@Valid T entity, BindingResult result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        baseService.edit((ID) entity.getClass().getMethod("getId").invoke(entity), entity);
        return "redirect:/"+ title_list + "/all";

    }

    @PostMapping("/delete")
    public String delete(@RequestBody ArrayList<ID> ids) {
        for (ID id : ids) {
            baseService.delete(id);
        }
        return "redirect:/"+ title_list + "/all";

    }

}
