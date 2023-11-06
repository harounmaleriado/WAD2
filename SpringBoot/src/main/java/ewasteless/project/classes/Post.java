package ewasteless.project.classes;

import java.time.Instant;
import java.util.List;

import com.google.cloud.firestore.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private String title ;
    private String message;

    private String username ;
    private List<DocumentReference> comments;
    private Instant createdTimestamp;
}
