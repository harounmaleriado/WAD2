package ewasteless.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import ewasteless.project.classes.User;

@Service
public class UserService {

    @Autowired
    private Firestore dbFirestore;
    // private Firestore dbFirestore = FirestoreClient.getFirestore();

    // public void signup(UserSignupRequest userSignupRequest) throws Exception {
    //     // Use Firebase Authentication SDK to sign up the user
    //     // Example pseudocode:
        
    //     FirebaseAuth.getInstance().createUserWithEmailAndPassword(userSignupRequest.getEmail(), userSignupRequest.getPassword())
    //         .addOnCompleteListener(task -> {
    //             if (task.isSuccessful()) {
    //                 // Get UID and other details
    //                 String UID = task.getResult().getUser().getUid();
                    
    //                 // Save the user details in Firestore
    //                 User user = new User();
    //                 user.setUID(UID);
    //                 user.setEmail(userSignupRequest.getEmail());
    //                 user.setEnabled(false); // Set to false initially, set to true once email is verified
    //                 // ... set other fields ...
    //                 dbFirestore.collection("users").document(UID).set(user);
    //             } else {
    //                 throw new Exception("Signup failed!");
    //             }
    //         });
        
    // }

    public User createUser(User user) {
    //     // // Automatically generate a unique document ID for the new document.
        dbFirestore.collection("users").add(user); 

    //     FirebaseAuth auth = FirebaseAuth.getInstance();
    //     auth.createUserWithEmailAndPassword("user@example.com", "password123").addOnCompleteListener(task -> {
    //         if (task.isSuccessful()) {
    //             FirebaseUser firebaseUser = auth.getCurrentUser();
    //             String uid = firebaseUser.getUid();
    //             User user = new User(uid, "user@example.com", ...);
    //             Firestore db = FirestoreClient.getFirestore();
    //             db.collection("users").document(uid).set(user);
    //         }
    //     });

    //     // better with auth. UID shld come from firebase auth, used as document ID
        // dbFirestore.collection("users").document(String.valueOf(user.getUID())).set(user);
        return user;
    }

    public boolean deleteUser(int UID) throws Exception {
        dbFirestore.collection("users").document(String.valueOf(UID)).delete().get();
        return true;
    }

    public User searchUserByUID(int UID) throws Exception {
        DocumentSnapshot document = dbFirestore.collection("users").document(String.valueOf(UID)).get().get();
        if (document.exists()) {
            return document.toObject(User.class);
        } else {
            return null;
        }
    }
    
}

