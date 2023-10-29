package ewasteless.project.service;



import ewasteless.project.classes.Product;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductService {

    @Autowired
    private Firestore dbFirestore;

    // private Firestore dbFirestore = FirestoreClient.getFirestore();

    // public String addProduct(Product product) throws Exception {
    //     DocumentReference addedDocRef = dbFirestore.collection("products").add(product).get(); 
    //     return addedDocRef.getId(); // Returns the ID of the newly added product.
    // }

    public Product getProductById(String productId) throws Exception {
        return dbFirestore.collection("products").document(productId).get().get().toObject(Product.class);
    }

    public List<Product> getProductsByCriteria(String type, Optional<String> model, Optional<String> brand, Optional<Double> benchmark) throws Exception {
        CollectionReference productCollection = dbFirestore.collection(type);
        Query query = productCollection;

        // Apply filtering based on criteria
        if (model.isPresent()) {
            query = query.whereEqualTo("model", model.get());
        }
        if (brand.isPresent()) {
            query = query.whereEqualTo("brand", brand.get());
        }
        if (benchmark.isPresent()) {
            query = query.whereEqualTo("benchmark", benchmark.get());
        }

        QuerySnapshot querySnapshot = query.get().get();

        List<Product> products = new ArrayList<>();
        for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
            products.add(doc.toObject(Product.class));
        }

        return products;
    }

    

    // ... other service methods ...
}

