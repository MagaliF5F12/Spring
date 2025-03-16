package com.example.magasklad.Controllers.API;

import com.example.magasklad.Models.Orders;
import com.example.magasklad.Models.Profile;
import com.example.magasklad.Models.Student;
import com.example.magasklad.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class APIOrders extends BaseAPI<Orders, UUID> {
    @Autowired
    protected APIOrders(BaseService<Orders, UUID> baseService) {
        super(baseService);
    }
}
