package ewasteless.project.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ewasteless.project.DTO.PostDTO;

import ewasteless.project.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        try {
            String updateTime = postService.createPost(postDTO);
            return ResponseEntity.ok(updateTime);
        } catch (ExecutionException | InterruptedException e) {
            // Handle exceptions properly
            return ResponseEntity.status(500).body("Error when creating the post: " + e.getMessage());
        }
    }
}
   

