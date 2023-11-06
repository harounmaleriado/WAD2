package ewasteless.project.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import ewasteless.project.DTO.PostDTO;
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
        Post post = new Post() ;
        post.setTitle(postDTO.getTitle());
        post.setMessage(postDTO.getMessage());
        post.setUsername(postDTO.getUsername());
        post.setComments(null);
        post.setCreatedTimestamp(Instant.now()); // Set the timestamp when creating a new post
        ApiFuture<WriteResult> future = firestore.collection("posts").document().create(post);
        WriteResult result = future.get();

        
        return result.getUpdateTime().toString();
    }
}

