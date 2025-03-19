package com.example.magasklad.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "users")
public class Users implements BaseModel{
    @Id
    @GeneratedValue
    UUID id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    String FIO;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    Profile profile;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "users")
    List<Defects> defects;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "users")
    List<FactoryProduct> factoryProducts;

    public Users() {
    }

    public Users(UUID id, String FIO, Profile profile, List<Defects> defects, List<FactoryProduct> factoryProducts) {
        this.id = id;
        this.FIO = FIO;
        this.profile = profile;
        this.defects = defects;
        this.factoryProducts = factoryProducts;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
        return new ArrayList<>(Arrays.asList("Идентификатор", "ФИО", "Профиль"));
    }

    @Override
    public LinkedHashMap<String, Object> getNewObject() {
        return new LinkedHashMap<>() {
            {
                put("FIO", Map.of("type", "text", "value", ""));
                put("profile", Map.of("type", "select-list", "value", ""));
            }};
    }

    @Override
    public ArrayList<Object> getDataAttributes() {
        return new ArrayList<>(Arrays.asList(FIO, profile.username));
    }

    @Override
    public String getStr() {
        return FIO;
    }

    @Override
    public String toString() {
        return FIO;
    }
}