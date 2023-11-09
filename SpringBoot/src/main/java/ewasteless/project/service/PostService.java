package ewasteless.project.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import com.google.cloud.firestore.Firestore;

import com.google.cloud.firestore.WriteResult;

import ewasteless.project.DTO.PostDTO;
import ewasteless.project.classes.Comment;
import ewasteless.project.classes.Post;
import ewasteless.project.classes.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {

    @Autowired
    private Firestore firestore;

    public String createPost(PostDTO postDTO) throws Exception {
    
        // Reference to the new post document with a generated ID
        DocumentReference newPostRef = firestore.collection("posts").document();
    
        // Create the Post object
        Post post = new Post();
        post.setPostID(newPostRef.getId()); // Set the generated document ID as the PostID
        post.setTitle(postDTO.getTitle());
        post.setMessage(postDTO.getMessage());
        post.setUsername(postDTO.getUsername());
        post.setUID(postDTO.getUID());
        post.setCreatedTimestamp(Instant.now()); 
    
        
        ApiFuture<WriteResult> futurePost = newPostRef.set(post);
    
    
        DocumentReference userRef = firestore.collection("users").document(postDTO.getUID());
       
        ApiFuture<DocumentSnapshot> futureUserSnapshot = userRef.get();
    
     
        DocumentSnapshot userSnapshot = futureUserSnapshot.get();
    
        if (userSnapshot.exists()) {
            User user = userSnapshot.toObject(User.class);
            int currentForumScore = user.getForumScore();
    
            // Increment the forumScore by 2
            int newForumScore = currentForumScore + 2;
            
            // Update the user's forumScore
            ApiFuture<WriteResult> futureUpdateUser = userRef.update("forumScore", newForumScore);
    
            
            WriteResult userUpdateResult = futureUpdateUser.get();
        
            } else {              
                throw new Exception("User does not exist with ID: " + postDTO.getUID());
            }         
            return newPostRef.get().get().getUpdateTime().toString();
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


