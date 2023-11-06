package ewasteless.project.classes;

import com.google.cloud.firestore.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Comment {
    private String comment;
    private DocumentReference creatorUID; // Assuming this is the creator's UID
    private Instant createdTimestamp; // This will store the creation timestamp
}

