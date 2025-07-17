package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3002", allowCredentials = "true")
@RequiredArgsConstructor
public class LoginController {

    private final UsersRepository usersRepo;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData, HttpServletRequest request) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Users user = usersRepo.findByEmail(username);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Email hoặc mật khẩu không đúng"));
        }

        Map<String, Object> claims = Map.of(
                "id", user.getId_user(),
                "email", user.getEmail(),
                "role", user.getRole()
        );
        String token = jwtUtil.generateToken(claims, String.valueOf(user.getId_user()));

        return ResponseEntity.ok(Map.of(
                "token", token,
                "name", user.getName(),
                "role", user.getRole()
        ));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Đăng xuất thành công");
    }
}
