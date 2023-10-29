package ewasteless.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ewasteless.project.service.UserAuthenticationService;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
        @RequestParam String email, 
        @RequestParam String password, 
        @RequestParam String name, 
        @RequestParam String username) {
        return ResponseEntity.ok(authService.signUp(email, password, name, username));
    }

    @PostMapping("/login")
    public ResponseEntity<String> signIn(
        @RequestParam String email, 
        @RequestParam String password) {
        return ResponseEntity.ok(authService.signIn(email, password));
    }
}