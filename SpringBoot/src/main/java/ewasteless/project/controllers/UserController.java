package ewasteless.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ewasteless.project.classes.User;
import ewasteless.project.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

 

    @DeleteMapping("/{UID}")
    public ResponseEntity<Void> deleteUser(@PathVariable int UID) {
        try {
            userService.deleteUser(UID);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{UID}")
    public ResponseEntity<User> getUser(@PathVariable int UID) {
        try {
            User user = userService.searchUserByUID(UID);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

