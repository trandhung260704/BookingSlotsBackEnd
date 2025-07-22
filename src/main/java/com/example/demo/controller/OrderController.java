package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Orders;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3002", allowCredentials = "true")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO,
                                         @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Thiếu hoặc sai định dạng Authorization header");
        }

        String token = authHeader.substring(7);

        try {
            Integer idUser = Integer.parseInt(jwtUtil.extractClaim(token, claims -> claims.get("id").toString()));
            orderDTO.setIdUser(idUser);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token không hợp lệ hoặc đã hết hạn");
        }

        Orders order = orderService.createOrder(orderDTO);
        if (order == null) {
            return ResponseEntity.status(409).body("Khung giờ đã được đặt. Vui lòng chọn khung khác.");
        }

        return ResponseEntity.ok(order);
    }
}
