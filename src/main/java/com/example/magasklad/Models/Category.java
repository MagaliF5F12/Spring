package com.example.magasklad.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.*;

@Entity
public class Category implements BaseModel{
    @Override
    public ArrayList<String> getColumns() {
        return new ArrayList<>(Arrays.asList("Идентификатор", "Название"));
    }

    @Override
    public LinkedHashMap<String, Object> getNewObject() {
        return new LinkedHashMap<>(){{
           put("title", Map.of("type", "text", "value", ""));
        }};
    }

    @Override
    public ArrayList<Object> getDataAttributes() {
        return new ArrayList<>(Arrays.asList(title));
    }

    @Override
    public String getStr() {
        return title;
    }

    @Id
    @GeneratedValue
    UUID id;

    @NotBlank
    String title;

    @OneToMany(mappedBy = "category")
    List<Product> products;

    public Category() {
    }

    public Category(UUID id, String title, List<Product> products) {
        this.id = id;
        this.title = title;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return title;
    }
}
