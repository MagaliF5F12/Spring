package com.example.magasklad.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity
public class Factory implements BaseModel {
    @Id
    @GeneratedValue
    UUID id;

    @NotBlank
    @Size(min = 2, max = 50)
    String title;

    @NotBlank
    @Size(min = 2, max = 50)
    String address;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "factory")
    List<Employee> employees;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "factories")
    List<Product> products;

    public Factory() {
    }

    public Factory(UUID id, String title, String address, List<Employee> employees, List<Product> products) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.employees = employees;
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public ArrayList<String> getColumns() {
        return new ArrayList<>(Arrays.asList("Идентификатор", "Название", "Адрес"));
    }

    @Override
    public LinkedHashMap<String, Object> getNewObject() {
        return new LinkedHashMap<>() {
            {
                put("title", Map.of("type", "text", "value", ""));
                put("address", Map.of("type", "text", "value", ""));
            }};
    }

    @Override
    public ArrayList<Object> getDataAttributes() {
        return new ArrayList<>(Arrays.asList(title, address));
    }

    @Override
    public String getStr() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
