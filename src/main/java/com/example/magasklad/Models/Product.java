package com.example.magasklad.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity
public class Product implements BaseModel {
    @Id
    @GeneratedValue
    UUID id;

    @NotBlank
    @Size(min = 2, max = 50)
    String title;

    @NotBlank
    @Size(min = 2, max = 50)
    String description;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    Factory factories;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    Category category;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "product")
    List<Defects> defects;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "product")
    List<FactoryProduct> factoryProducts;

    public Product() {
    }

    public Product(UUID id, String title, String description, Factory factories, Category category, List<Defects> defects, List<FactoryProduct> factoryProducts) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.factories = factories;
        this.category = category;
        this.defects = defects;
        this.factoryProducts = factoryProducts;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Factory getFactories() {
        return factories;
    }

    public void setFactories(Factory factories) {
        this.factories = factories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Defects> getDefects() {
        return defects;
    }

    public void setDefects(List<Defects> defects) {
        this.defects = defects;
    }

    public List<FactoryProduct> getFactoryProducts() {
        return factoryProducts;
    }

    public void setFactoryProducts(List<FactoryProduct> factoryProducts) {
        this.factoryProducts = factoryProducts;
    }

    @Override
    public ArrayList<String> getColumns() {
        return new ArrayList<>(Arrays.asList("Идентификатор", "Название", "Описание", "Завод", "Категория"));
    }

    @Override
    public LinkedHashMap<String, Object> getNewObject() {
        return new LinkedHashMap<>() {
            {
                put("title", Map.of("type", "text", "value", ""));
                put("description", Map.of("type", "text", "value", ""));
                put("factories", Map.of("type", "select-list", "value", new ArrayList<Factory>()));
                put("category", Map.of("type", "select-list", "value", new ArrayList<Category>()));
            }};
    }

    @Override
    public ArrayList getDataAttributes() {
        return new ArrayList(Arrays.asList(title, description, factories.title, category.title));
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
