package ewasteless.project.service;

import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;

import ewasteless.project.DTO.ChatDTO;
import ewasteless.project.classes.Chat;
import ewasteless.project.classes.Comment;
import ewasteless.project.classes.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Service
public class ChatService {

    private final Firestore firestore;

    @Autowired
    public ChatService(Firestore firestore) {
        this.firestore = firestore;
    }

    public String createChat(ChatDTO chatDTO) throws ExecutionException, InterruptedException {
        // Reference to the new chat document with a generated ID
        DocumentReference newChatRef = firestore.collection("chats").document();
    
        // Create the Chat object using the DTO
        Chat chat = new Chat();
        chat.setCid(newChatRef.getId()); // Set the generated document ID as the CID
        chat.setBid(chatDTO.getBid());
        chat.setSid(chatDTO.getSid());
        chat.setBrand(chatDTO.getBrand());
        chat.setModel(chatDTO.getModel());

        // Save the chat object to Firestore
        ApiFuture<WriteResult> futureChat = newChatRef.set(chat);
        // Wait for the operation to complete and retrieve the write result
        WriteResult writeResult = futureChat.get();

        // Return the created chat document ID or other relevant information
        return newChatRef.getId();
    }

    

    public String addCommentToChat(String chatId, Comment comment) throws Exception {
        comment.setCreatedTimestamp(Instant.now());
        DocumentReference postRef = firestore.collection("chats").document(chatId);
        ApiFuture<WriteResult> writeResultApiFuture = postRef.collection("comments").document().create(comment);
        WriteResult writeResult = writeResultApiFuture.get();
        return writeResult.getUpdateTime().toString();
    }

    public void deleteChat(String chatId) throws ExecutionException, InterruptedException {
        // Attempt to delete the document from the 'listings' collection
        firestore.collection("listings").document(chatId).delete().get();
    }
    
}

