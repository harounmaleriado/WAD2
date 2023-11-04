package ewasteless.project.service;

// Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Firebase imports
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

// Java imports
import java.util.HashMap;
import java.util.Map;

// Model imports
import ewasteless.project.DTO.UserDTO;

@Service
public class UserRegistrationService {

    @Autowired
    private Firestore dbFirestore;

    // Expects User object from UserRegistrationController 
    // Returns UID of successful new user
    public String signUp(UserDTO user) {
        try {
            
            UserRecord.CreateRequest AccountCreationRequest = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(AccountCreationRequest);
            

            // Create user in firestore with matching DocID
            DocumentReference userReference = dbFirestore.collection("users").document(userRecord.getUid());
            Map<String, Object> userProfileMap = new HashMap<>();

            // Populate user fields with:
            userProfileMap.put("email", user.getEmail());
            userProfileMap.put("name", user.getName());
            userProfileMap.put("username", user.getUsername());
            userProfileMap.put("UID", userRecord.getUid());
            // ... add any other fields you want here
            userReference.set(userProfileMap);
            return userRecord.getUid(); 
        } catch (FirebaseAuthException e) {
            // handle exception
            throw new RuntimeException("Failed to register user");
        }
    }
}

