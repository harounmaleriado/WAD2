package ewasteless.project.controllers;


import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ewasteless.project.DTO.ChatDTO;
import ewasteless.project.DTO.CommentDTO;
import ewasteless.project.classes.Chat;
import ewasteless.project.classes.Comment;
import ewasteless.project.service.ChatService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService ChatService;

    @PostMapping
    public ResponseEntity<String> createChat(@RequestBody ChatDTO chatDTO) {
        try {
            String updateTime = ChatService.createChat(chatDTO);
            return ResponseEntity.ok(updateTime);
        } catch (ExecutionException | InterruptedException e) {
            // Handle exceptions properly
            return ResponseEntity.status(500).body("Error when creating the chat: " + e.getMessage());
        }
    }


    @PostMapping("/{chatId}/comments")
    public ResponseEntity<String> addCommentToPost(
            @PathVariable String chatId,
            @RequestBody CommentDTO commentDTO) throws Exception {
            
                

        try {
         
            // Map the DTO to your Comment entity
            Comment comment = new Comment();
            comment.setUsername(commentDTO.getUsername());
            comment.setComment(commentDTO.getComment());
            comment.setUid(commentDTO.getUid()) ;
            // Set any other fields required by your Comment entity

            String response = ChatService.addCommentToChat(chatId, comment);
            return ResponseEntity.ok(response);
        } catch (ExecutionException | InterruptedException e) {
            // Handle the exceptions properly here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable String chatId) {
        try {
            ChatService.deleteChat(chatId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    
}

