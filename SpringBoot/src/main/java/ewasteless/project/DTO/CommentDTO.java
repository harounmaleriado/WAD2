package ewasteless.project.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.firestore.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String comment;
    private String username; // Assuming this is the creator's UID

    @JsonProperty("uid")
    private String uid;
    
}
