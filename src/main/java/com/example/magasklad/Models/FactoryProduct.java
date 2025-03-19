package com.example.magasklad.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import java.util.*;

@Entity
public class FactoryProduct implements BaseModel {
    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne
    Users users;

    @ManyToOne
    Product product;

    @NotBlank
    String date;

    public FactoryProduct() {
    }

    public FactoryProduct(UUID id, Users users, Product product, String date) {
        this.id = id;
        this.users = users;
        this.product = product;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users user) {
        this.users = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public ArrayList<String> getColumns() {
       return new ArrayList<>(Arrays.asList("Идентификатор", "Пользователи", "Продукты", "Дата создания"));
    }

    @Override
    public LinkedHashMap<String, Object> getNewObject() {
        return new LinkedHashMap<>(){{
           put("users", Map.of("type", "select-list", "value", new ArrayList<Users>()));
           put("product", Map.of("type", "select-list", "value", new ArrayList<Product>()));
           put("date", Map.of("type", "date", "value",""));
        }};
    }

    @Override
    public ArrayList<Object> getDataAttributes() {
        return new ArrayList<>(Arrays.asList(users.FIO, product.title, date));
    }

    @Override
    public String getStr() {
        return "";
    }
}
