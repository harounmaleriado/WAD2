package ewasteless.project.service;

import org.springframework.beans.factory.annotation.Autowired;
// Spring imports
import org.springframework.stereotype.Service;

// Firebase imports
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;    
// import com.google.firebase.cloud.FirestoreClient;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;

// Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Your application's model imports
import ewasteless.project.classes.Listing;

@Service
public class ListingService {

    @Autowired
    private Firestore dbFirestore;

    // private Firestore dbFirestore = FirestoreClient.getFirestore();

    public String addListing(Listing listing) throws InterruptedException, ExecutionException {
        DocumentReference addedDocRef = dbFirestore.collection("listings").add(listing).get(); // You might need to handle exceptions.
        return addedDocRef.getId(); // Returns the ID of the newly added listing.
    }

    public Listing getListingById(String listingId) throws Exception {
        return dbFirestore.collection("listings").document(listingId).get().get().toObject(Listing.class);
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
}


