package com.example.magasklad.Controllers.API;

import com.example.magasklad.Models.Roles;
import com.example.magasklad.Service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
public class APIRoles extends  BaseAPI<Roles, UUID> {
    protected APIRoles(BaseService<Roles, UUID> baseService) {
        super(baseService);
    }
}
