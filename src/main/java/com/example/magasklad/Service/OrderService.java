package com.example.magasklad.Service;

import com.example.magasklad.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService extends BaseService<Orders, UUID> {
    public OrderService(JpaRepository<Orders, UUID> repository) {
        super(repository);
    }
}
