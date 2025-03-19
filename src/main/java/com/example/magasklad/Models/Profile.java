package com.example.magasklad.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "profile")
public class Profile implements BaseModel {
    @Id
    @GeneratedValue
    UUID id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Length(min = 4, message = "Пароль должен содержать минимум 4 символов")
    String password;

    boolean active;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<RoleEnum> roles;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne(mappedBy = "profile")
    Users users;

    public ArrayList<String> getColumns() {
        return new ArrayList<>(Arrays.asList("Идентификатор", "Пользовательское имя", "Пароль", "Роли", "Активен ли профиль"));
    }
    public ArrayList<Object> getDataAttributes() {
        return new ArrayList<>(Arrays.asList(username, password, roles.stream().map(Enum::name).collect(Collectors.joining(", ")), active));
    }
    public LinkedHashMap<String, Object> getNewObject(){
        return new LinkedHashMap<>(){
            {
                put("username", Map.of("type", "text", "value", ""));
                put("password", Map.of("type", "password", "value", ""));
                put("roles", Map.of("type", "select-multiple", "value", List.of(RoleEnum.values())));
                put("active", Map.of("type", "boolean", "value", true));
            }};
    }

    public String getStr() {
        return username;
    }
    @Override
    public String toString() {
        return username;
    }

    public Profile() {
    }

    public Profile(UUID id, String username, String password, boolean active, Set<RoleEnum> roles, Users users) {
        this.id = id;
        this.username = username;
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
