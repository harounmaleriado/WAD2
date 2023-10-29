package ewasteless.project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ewasteless.project.DTO.UserDTO;
import ewasteless.project.service.UserAuthenticationService;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService authService;

    
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(authService.signUp(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> signIn(@RequestBody UserDTO user) {
        return ResponseEntity.ok(authService.signIn(user.getEmail(), user.getPassword()));
    }
}