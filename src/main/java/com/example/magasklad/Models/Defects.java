package com.example.magasklad.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Defects implements BaseModel {
    @Override
    public ArrayList<String> getColumns() {
        return new ArrayList<>(Arrays.asList("Идентификатор", "Сотрудник", "Продукт", "Дата обнаружения", "описание дефекта", "Статус"));
    }

    @Override
    public LinkedHashMap<String, Object> getNewObject() {
        return new LinkedHashMap<>() {
            {
                put("users", Map.of("type", "select-list", "value", new ArrayList<Users>()));
                put("product", Map.of("type", "select-list", "value", new ArrayList<Product>()));
                put("date", Map.of("type", "date", "value", List.of(DefectStatus.values())));
                put("description", Map.of("type", "text", "value", ""));
                put("status", Map.of("type", "select", "value", List.of(DefectStatus.values())));
            }};
    }

    @Override
    public ArrayList<Object> getDataAttributes() {
        return new ArrayList<>(Arrays.asList(users != null ? users.FIO : "null", product.title, date, description, status.name()));
    }

    @Override
    public String getStr() {
        return (users != null ? users.FIO : "null") + " - " + product.title + " " + date;
    }

    @Id
    @GeneratedValue
    UUID id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    Users users;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    Product product;

    @NotBlank
    String date;

    @NotBlank
    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    DefectStatus status;

    enum DefectStatus {
        OPEN, IN_PROGRESS, RESOLVED, CLOSED
    }

    public Defects() {
    }

    public Defects(UUID id, Users users, Product product, String date, String description, DefectStatus status) {
        this.id = id;
        this.users = users;
        this.product = product;
        this.date = date;
        this.description = description;
        this.status = status;
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

    public void setUsers(Users users) {
        this.users = users;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DefectStatus getStatus() {
        return status;
    }

    public void setStatus(DefectStatus status) {
        this.status = status;
    }
}
