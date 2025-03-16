package com.example.magasklad.Controllers.API;

import com.example.magasklad.Models.Users;
import com.example.magasklad.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class APIUsers extends BaseAPI<Users, UUID> {
    @Autowired
    protected APIUsers(BaseService<Users, UUID> baseService) {
        super(baseService);
    }
}
