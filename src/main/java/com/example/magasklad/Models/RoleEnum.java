package com.example.magasklad.Models;


import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER, ADMIN, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}