package ewasteless.project.service;

// Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Firebase imports
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;    
import com.google.cloud.firestore.QuerySnapshot;

// ApiFuture import
import com.google.api.core.ApiFuture;

// Model imports
import ewasteless.project.classes.Listing;

// Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ListingService {

    @Autowired
    private Firestore dbFirestore;

    // private Firestore dbFirestore = FirestoreClient.getFirestore();

    public String addListing(String UID, 
                            String PID, 
                            double price, 
                            String productDescription, 
                            int postalCode) 
                            throws ExecutionException, InterruptedException {
        // Create DocumentReference objects for seller and product
       
        
        DocumentReference sellerRef = dbFirestore.collection("users").document(UID);
        DocumentReference productRef = dbFirestore.collection("CPU").document(PID);

        // Create Listing object
        Listing listing = new Listing(sellerRef, productRef, price, productDescription, postalCode);

        // Add the listing to Firestore asynchronously
        ApiFuture<DocumentReference> future = dbFirestore.collection("listings").add(listing);

        // Wait for the operation to complete and retrieve the result
        DocumentReference newListingRef = future.get();
        return newListingRef.getId();
    }

    public Listing getListingById(String LID) throws Exception {
        return dbFirestore.collection("listings").document(LID).get().get().toObject(Listing.class);
    }

    public List<Listing> searchListings(String brand, String model, Double price, String sellerId) throws Exception {
        CollectionReference listings = dbFirestore.collection("listings");
        Query query = listings;
    
        if (brand != null) {
            query = query.whereEqualTo("brand", brand);
        }
        if (model != null) {
            query = query.whereEqualTo("model", model);
        }
        if (price != null) {
            query = query.whereLessThanOrEqualTo("price", price); 
        }
        if (sellerId != null) {
            query = query.whereEqualTo("sellerId", sellerId);
        }
    
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        
        List<Listing> results = new ArrayList<>();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            results.add(doc.toObject(Listing.class));
        }
    
        return results;
    }

    public void deleteListing(String LID) throws ExecutionException, InterruptedException {
        // Attempt to delete the document from the 'listings' collection
        dbFirestore.collection("listings").document(LID).delete().get();
    }
}


