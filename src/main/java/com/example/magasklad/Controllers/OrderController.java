package com.example.magasklad.Controllers;


import com.example.magasklad.Models.Orders;
import com.example.magasklad.Models.Student;
import com.example.magasklad.Service.OrderService;
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
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    public OrderService orderService;


    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<Orders> roles = orderService.findAll();
        System.out.println("количество заказов: " + roles.size());
        model.addAttribute("ordersList", roles);
        model.addAttribute("order", new Orders());

        return "orders";

    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("orders") Orders role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Orders> roles = orderService.findAll();
            System.out.println("количество заказов: " + roles.size());
            model.addAttribute("ordersList", roles);
            model.addAttribute("order", new Orders());
        }
        orderService.add(role);
        return "redirect:/orders/all";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("orders") Orders order, BindingResult result) {
        orderService.edit(order.getId(), order);
        return "redirect:/orders/all";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody ArrayList<UUID> ids) {
        for(UUID id : ids) {
            orderService.delete(id);
        }
        return "redirect:/orders/all";

    }
    @GetMapping("/all/{id}")
    public String getIdStudent(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("orderById", orderService.findById(id));
        model.addAttribute("order", new Student());
        return "orders";
    }

}
