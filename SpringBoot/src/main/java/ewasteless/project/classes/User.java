package ewasteless.project.classes;

import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.DocumentReference;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class User {

    private String UID ;
    private String name;
    private String username;
    private String email;

    private boolean enabled; // To check if email is verified

    // A map of product type to product ID reference
    // private Map<String, String> products;

    // A list of listing IDs associated with the user
    private List<DocumentReference> listings;

    public User(String name, String username, String email) {
        
        this.name = name;
        this.username = username;
        this.email = email;
    }
}
