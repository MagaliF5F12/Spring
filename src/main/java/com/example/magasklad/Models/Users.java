package com.example.magasklad.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    String firstName;
    @NotNull
    String surName;
    @Nullable
    String lastName;
    @NotEmpty
    @Size(min = 2, max = 10, message = "Длина должна быть от 2 до 10 символов")
    String role;
    @Value("${some.value}")
    boolean isDeleted = false;

    public Users(){}

    public Users(Long id, @NotNull String firstName, @NotNull String surName,@Nullable String lastName, String role) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.role = role;
    }

    public Users(@NotNull String firstName, @NotNull String surName,@Nullable String lastName, String role) {
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getSurName() {
        return surName;
    }

    public void setSurName(@NotNull String surName) {
        this.surName = surName;
    }

    public @Nullable String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty String getRole() {
        return role;
    }

    public void setRole(@NotEmpty String role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

