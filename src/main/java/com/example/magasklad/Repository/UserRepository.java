package com.example.magasklad.Repository;

import com.example.magasklad.Models.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    AtomicInteger idCounter = new AtomicInteger(1);
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users.stream().filter(user -> !user.isDeleted()).collect(Collectors.toCollection(ArrayList::new));
    }

    public User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public boolean addUser(User user) {
        user.setId(idCounter.getAndIncrement());
        users.add(user);
        return true;
    }

    public boolean editUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setDeleted(true);
                return true;
            }
        }
        return false;
    }
}
