package ewasteless.project.classes;

import com.google.cloud.firestore.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Listing {

    // reference to seller
    private DocumentReference UID ;

    // reference to product sold
    private DocumentReference PID ;

    private double price ;
    
    private String productDescription ;

    private int postalCode ;
}
