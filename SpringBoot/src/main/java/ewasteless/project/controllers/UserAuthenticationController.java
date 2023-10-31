package ewasteless.project.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    
    // @PostMapping("/signup")
    // public ResponseEntity<Map<String, String>> signUp(@Valid @RequestBody UserDTO user) {
    //     String uid = authService.signUp(user);
    //     Map<String, String> response = new HashMap<>();
    //     response.put("status", "OK");
    //     response.put("uid", uid);
    //     return ResponseEntity.ok(response);
    // }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO user) {
        try {
            String userId = authService.signUp(user);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "OK");
            response.put("uid", userId);
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody UserDTO user) {
        try {
            String userId = authService.signIn(user.getEmail(), user.getPassword());
            Map<String, Object> response = new HashMap<>();
            response.put("status", "OK");
            response.put("uid", userId);
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}