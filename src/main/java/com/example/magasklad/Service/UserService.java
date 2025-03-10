package com.example.magasklad.Service;

import com.example.magasklad.Models.Pagination;
import com.example.magasklad.Models.User;
import com.example.magasklad.Repository.UserRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Pagination<User> getAll(int page, @Nullable String firstName, @Nullable String surName, @Nullable String lastName, @Nullable String role) {
        ArrayList<User> users = userRepository.getUsers();
        System.out.println("users: " + users.size());
        if (firstName != null && !firstName.isEmpty())
            users = users.stream().filter(user -> user.getFirstName().equalsIgnoreCase(firstName)).collect(Collectors.toCollection(ArrayList::new));
        if (surName != null)
            users = users.stream().filter(user -> user.getSurname().equalsIgnoreCase(surName)).collect(Collectors.toCollection(ArrayList::new));
        if (lastName != null)
            users = users.stream().filter(user -> user.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toCollection(ArrayList::new));
        if (role != null)
            users = users.stream().filter(user -> user.getRole().equalsIgnoreCase(role)).collect(Collectors.toCollection(ArrayList::new));
        return new Pagination<User>(users, page);
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    public boolean editUser(User user) {
        return userRepository.editUser(user);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public HashSet<String> GetAllCategory() {
        HashSet<String> hashSet = new HashSet<>();

        for (User user : userRepository.getUsers())
            hashSet.add(user.getFirstName());

        return hashSet;
    }

}
