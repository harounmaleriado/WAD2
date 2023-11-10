package ewasteless.project.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {

    
    

    @JsonProperty("bid")
    private String bid ;

    @JsonProperty("sid")
    private String sid ;

    private String brand ;
    private String model ;
}