package com.example.magasklad.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Employee implements BaseModel {
    @Override
    public ArrayList<String> getColumns() {
        return new ArrayList<>(Arrays.asList("Идентификатор", "Сотрудник", "Завод"));
    }

    @Override
    public LinkedHashMap<String, Object> getNewObject() {
        return new LinkedHashMap<>(){{
            put("users", Map.of("type", "select-list", "value", new ArrayList<Users>()));
            put("factory", Map.of("type", "select-list", "value", new ArrayList<Factory>()));
        }};
    }

    @Override
    public ArrayList<Object> getDataAttributes() {
        return new ArrayList<>(Arrays.asList(users.FIO, factory.title));
    }

    @Override
    public String getStr() {
        return "";
    }

    @Id
    @GeneratedValue
    UUID id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    Users users;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    Factory factory;

    public Employee() {
    }

    public Employee(UUID id, Users users, Factory factory) {
        this.id = id;
        this.users = users;
        this.factory = factory;
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

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
