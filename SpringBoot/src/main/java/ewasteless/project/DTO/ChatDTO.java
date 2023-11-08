package ewasteless.project.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private String message;
    private String username; // Assuming this is the creator's UID
    
}