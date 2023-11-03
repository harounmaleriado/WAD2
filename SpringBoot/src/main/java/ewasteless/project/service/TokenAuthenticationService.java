package ewasteless.project.service;

import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

@Service
public class TokenAuthenticationService {

    public FirebaseToken validateToken(String idToken) {
        try {
            return FirebaseAuth.getInstance().verifyIdToken(idToken);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage()); // or return a custom error message/response
        }
    }
}

