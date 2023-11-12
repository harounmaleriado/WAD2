package ewasteless.project.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
