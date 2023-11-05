package ewasteless.project;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
// import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;


@Configuration
public class FirebaseConfig {

    

    @PostConstruct
    public void initialize() {
        try {
            // Firebase SDK credential file
            FileInputStream serviceAccount = 
            // new FileInputStream("/Users/jonathanholton/Documents/SMU Academics/Yr 2 Sem 1/IS216/Project/SpringBoot/src/main/resources/is216-proj-76064-firebase-adminsdk-7j8ba-871e4404cb.json");
            new FileInputStream("SpringBoot/src/main/resources/FirebaseKey.json");
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                // .setDatabaseUrl("https://is216-e-wasteless-default-rtdb.asia-southeast1.firebasedatabase.app")
                .build();
            // FirebaseOptions options = FirebaseOptions.builder()
            //         .setCredentials(GoogleCredentials.getApplicationDefault())
            //         .build();

            // ??
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Firestore firestore() {
        FirebaseApp defaultApp = FirebaseApp.getInstance();  // Ensure FirebaseApp is initialized
        return FirestoreClient.getFirestore(defaultApp);
    }
}

