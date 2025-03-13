package com.example.magasklad.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue
    UUID id;
    @Size(min = 5, max = 50)
    String login;
    @Size(min = 6, max = 20)
    String password;

    @OneToOne(mappedBy = "profile")
    Users users;

    public Profile(){}

    public Profile(UUID id, String login, String password, Users users) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @Size(min = 5, max = 50) String getLogin() {
        return login;
    }

    public void setLogin(@Size(min = 5, max = 50) String login) {
        this.login = login;
    }

    public @Size(min = 6, max = 20) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20) String password) {
        this.password = password;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
