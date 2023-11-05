package ewasteless.project;

import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.google.cloud.firestore.DocumentReference;
// import com.google.cloud.firestore.Firestore;
// import com.google.firebase.cloud.FirestoreClient;

// import ewasteless.project.classes.Listing;
// import ewasteless.project.controllers.ListingController;
// import ewasteless.project.service.ProductUpdateService;


@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
        
		SpringApplication.run(ProjectApplication.class, args);

        // update product collections
        // ProductUpdateService.uploadDataFromCSV("src/main/resources/static/Data/GPU_UserBenchmarks.csv") ;      
	}
}

