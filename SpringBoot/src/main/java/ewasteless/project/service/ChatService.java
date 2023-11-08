package ewasteless.project.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import ewasteless.project.DTO.ChatDTO;
import ewasteless.project.DTO.PostDTO;
import ewasteless.project.classes.Comment;
import ewasteless.project.classes.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Service
public class ChatService {

    @Autowired
    private Firestore firestore;

    public String createChat(ChatDTO chatDTO) throws ExecutionException, InterruptedException {
        // Create a new document reference with a generated ID
        DocumentReference newChatRef = firestore.collection("chats").document();
    
      
    
        // Use the 'set' method to create the document with the Post object.
        // The 'set' method is used instead of 'create' to allow setting the document ID inside the document.
        ApiFuture<WriteResult> future = newChatRef.set(chatDTO);
        WriteResult result = future.get();
    
        return result.toString();
    }
}
