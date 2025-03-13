package com.example.magasklad.Controllers;


import com.example.magasklad.Models.Orders;
import com.example.magasklad.Models.Users;
import com.example.magasklad.Service.OrderService;
import com.example.magasklad.Service.ProfileService;
import com.example.magasklad.Service.RolesService;
import com.example.magasklad.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class MainController {
    @Autowired
    public UserService userService;
    @Autowired
    public RolesService rolesService;
    @Autowired
    public ProfileService profileService;
    @Autowired
    public OrderService orderService;


    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<Users> users = userService.findAll();
        System.out.println("количество пользователей: " + users.size());
        model.addAttribute("pagination_users", users);
        model.addAttribute("user", new Users());
        model.addAttribute("roles", rolesService.findAll());
        model.addAttribute("profiles", profileService.findAll());
        model.addAttribute("ordersList", orderService.findAll());
        model.addAttribute("user_orders_list", null);
        model.addAttribute("doing", false);

        return "users";

    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("users") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Users> users = userService.findAll();
            model.addAttribute("pagination_users", users);
            model.addAttribute("users", userService.findAll());
            return "users";
        }
        userService.add(user);
        return "redirect:/users/all";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("users") Users user, BindingResult result) {
        userService.edit(user.getId(), user);
        return "redirect:/users/all";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody ArrayList<UUID> ids) {
        for (UUID id : ids) {
            userService.delete(id);
        }
        return "redirect:/users/all";

    }

    @GetMapping("/all/{id}")
    public String getIdStudent(@PathVariable("id") UUID id, Model model) {
        List<Users> users = userService.findAll();
        List<Orders> orders = userService.findById(id).getOrders() == null ? new ArrayList<>() : userService.findById(id).getOrders();
        System.out.println("количество заказов: " + orders.size());
        model.addAttribute("pagination_users", users);
        model.addAttribute("user", new Users());
        model.addAttribute("roles", rolesService.findAll());
        model.addAttribute("profiles", profileService.findAll());
        model.addAttribute("ordersList", orderService.findAll());
        model.addAttribute("user_orders_list", orders);
        model.addAttribute("doing", true);
        model.addAttribute("user_id", id);
        model.addAttribute("user_name", userService.findById(id).getFirstName());
        return "users";
    }

    @PostMapping("/all/{id}/add_order")
    public String createOrder(@RequestParam("order_id") UUID order_id,
                              @RequestParam("user_id") UUID userId,
                              @PathVariable("id") UUID id,
                              Model model) {
        Users user = userService.findById(userId);
        List<Orders> orders_user = user.getOrders();
        orders_user.add(orderService.findById(order_id));
        user.setOrders(orders_user);
        userService.edit(user.getId(), user);
        return "redirect:/users/all/{id}";
    }


}