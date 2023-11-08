package ewasteless.project.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import ewasteless.project.DTO.PostDTO;
import ewasteless.project.classes.Comment;
import ewasteless.project.classes.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {

    @Autowired
    private Firestore firestore;

    public String createPost(PostDTO postDTO) throws ExecutionException, InterruptedException {
        // Create a new document reference with a generated ID
        DocumentReference newPostRef = firestore.collection("posts").document();
    
        // Create the Post object
        Post post = new Post();
        post.setPostID(newPostRef.getId()); // Set the generated document ID as the PostID
        post.setTitle(postDTO.getTitle());
        post.setMessage(postDTO.getMessage());
        post.setUsername(postDTO.getUsername());
        post.setCreatedTimestamp(Instant.now()); // Set the timestamp when creating a new post
    
        // Use the 'set' method to create the document with the Post object.
        // The 'set' method is used instead of 'create' to allow setting the document ID inside the document.
        ApiFuture<WriteResult> future = newPostRef.set(post);
        WriteResult result = future.get();
    
        return result.getUpdateTime().toString();
    }
    

    public String addCommentToPost(String postId, Comment comment) throws ExecutionException, InterruptedException {
        // Set the created timestamp for the comment
        comment.setCreatedTimestamp(Instant.now());

        // Get a reference to the post document by its ID
        DocumentReference postRef = firestore.collection("posts").document(postId);
        
        // Create a new comment document within the 'comments' subcollection of the post
        ApiFuture<WriteResult> writeResult = postRef.collection("comments").document().create(comment);
        
        // Wait for the future to complete and get the write result
        WriteResult result = writeResult.get();
        
        return result.getUpdateTime().toString();
    }
}


