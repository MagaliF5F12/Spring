package com.example.magasklad.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue
    UUID id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Length(min = 8, message = "Пароль должен содержать минимум 8 символов")
    String password;
    boolean active;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<RoleEnum> roles;

    @OneToOne(mappedBy = "profile")
    Users users;

    public Profile() {
    }

    public Profile(UUID id, String username, String password, boolean active, Users users) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.users = users;
    }

    public Profile(String username, String password, boolean active, boolean b, Set<RoleEnum> roles, Users users) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.users = users;
    }

    public Profile(UUID id, String login, String password, boolean active, Set<RoleEnum> roles, Users users) {
        this.id = id;
        this.username = login;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
