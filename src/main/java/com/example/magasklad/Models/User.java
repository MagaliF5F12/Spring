package com.example.magasklad.Models;

import jakarta.annotation.Nullable;

public class User {
    int id;
    String firstName;
    String surName;
    @Nullable
    String lastName;
    String role;
    boolean isDeleted = false;

    public User(int id, String firstName, String surname, String lastName, String role) {
        this.id = id;
        this.firstName = firstName;
        surName = surname;
        this.lastName = lastName;
        this.role = role;
    }

    public User(String firstName, String surname, String lastName, String role) {
        this.firstName = firstName;
        surName = surname;
        this.lastName = lastName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surName;
    }

    public void setSurname(String surname) {
        surName = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
