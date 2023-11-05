package ewasteless.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListingDTO {

    @Override
    public String toString() {
        return "{" +
            " UID='" + getUID() + "'" +
            ", PID='" + getPID() + "'" +
            ", price='" + getPrice() + "'" +
            ", productDescription='" + getProductDescription() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            "}";
    }

    // reference to seller
    @JsonProperty("UID")
    private String UID ;

    // reference to product sold
    @JsonProperty("PID")
    private String PID ;

    private double price ;
    
    private String productDescription ;

    private int postalCode ;
}
