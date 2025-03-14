package com.example.magasklad.Controllers.API;

import com.example.magasklad.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class BaseAPI<T, ID> {


    @Autowired
    private final BaseService<T, ID> baseService;

    protected BaseAPI(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }


    @GetMapping("/all")
    public List<T> getAll() {
        return baseService.findAll();
    }


    @GetMapping("/{id}")
    public T getById(@PathVariable ID id) {
        return baseService.findById(id);
    }

    @PostMapping("/all")
    public T add(@RequestBody T entity) {
        return baseService.add(entity);
    }

    @PutMapping("/all/{id}")
    public T edit(@PathVariable ID id, @RequestBody T entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        entity.getClass().getMethod("setId", id.getClass()).invoke(entity, id);
        return baseService.edit(id, entity);
    }

    @DeleteMapping("all/{id}")
    public void delete(@PathVariable ID id) {
        baseService.delete(id);
    }

}
