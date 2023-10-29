package ewasteless.project.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;

import ewasteless.project.classes.Product;

@Service
public class SearchService {

    @Autowired
    private Firestore firestore;

    public List<Product> searchProducts(SearchCriteria criteria) {
        CollectionReference productsRef = firestore.collection("products"); // assuming all products are in one collection

        Query query = productsRef;
        if (criteria.getBrand() != null) {
            query = query.whereEqualTo("brand", criteria.getBrand());
        }
        if (criteria.getModel() != null) {
            query = query.whereEqualTo("model", criteria.getModel());
        }
        if (criteria.getMinPrice() != null) {
            query = query.whereGreaterThanOrEqualTo("price", criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            query = query.whereLessThanOrEqualTo("price", criteria.getMaxPrice());
        }
        // ... add more criteria as needed

        // Execute the query
        List<Product> results = new ArrayList<>();
        try {
            for (DocumentSnapshot document : query.get().get().getDocuments()) {
                results.add(document.toObject(Product.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching search results", e);
        }

        return results;
    }
}

