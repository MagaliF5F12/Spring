package com.example.magasklad.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue
    UUID id;
    @NotNull
    String firstName;
    @NotNull
    String surName;
    @Nullable
    String lastName;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles roles;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @NotNull
    @OneToOne
    @JoinColumn(name = "profile_id")
    Profile profile;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany
    @JoinTable(name = "user_order",joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "orders_id"))
    List<Orders> orders;

    public Users(){}

    public Users(UUID id, String firstName, String surName, @Nullable String lastName, Roles roles, Profile profile, List<Orders> orders) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.roles = roles;
        this.profile = profile;
        this.orders = orders;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    public @NotNull Roles getRoles() {
        return roles;
    }

    public void setRoles(@NotNull Roles roles) {
        this.roles = roles;
    }

    public @NotNull Profile getProfile() {
        return profile;
    }

    public void setProfile(@NotNull Profile profile) {
        this.profile = profile;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}