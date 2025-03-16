package com.example.magasklad.Controllers.API;

import com.example.magasklad.Models.Profile;
import com.example.magasklad.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class APIProfiles extends  BaseAPI<Profile, UUID> {
    @Autowired
    protected APIProfiles(BaseService<Profile, UUID> baseService) {
        super(baseService);
    }
}
