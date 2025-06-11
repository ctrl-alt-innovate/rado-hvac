package com.evanwahrmund.server;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest, HttpSession session) {
        boolean isValid = userService.validateCredentials(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
       if (isValid) {
           session.setAttribute("username", loginRequest.getUsername());
           return ResponseEntity.ok().build();
       }
       else {
           return ResponseEntity.status(401).body("Invalid credentials");
       }
    }
}
