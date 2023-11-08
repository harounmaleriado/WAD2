package ewasteless.project.controllers;


import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ewasteless.project.DTO.ChatDTO;
import ewasteless.project.service.ChatService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService ChatService;

    @PostMapping
    public ResponseEntity<String> createChat(@RequestBody ChatDTO ChatDTO) {
        try {
            String updateTime = ChatService.createChat(ChatDTO);
            return ResponseEntity.ok(updateTime);
        } catch (ExecutionException | InterruptedException e) {
            // Handle exceptions properly
            return ResponseEntity.status(500).body("Error when creating the chat: " + e.getMessage());
        }
    }
}
