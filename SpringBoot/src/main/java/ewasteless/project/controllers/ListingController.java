package ewasteless.project.controllers;

// Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Model imports
import ewasteless.project.classes.Listing;
import ewasteless.project.service.ListingService;

// Java imports
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @PostMapping
    public ResponseEntity<String> addListing(@RequestParam String sellerId, 
                                            @RequestParam String productId,
                                             @RequestParam double price, 
                                            @RequestParam String productDescription,
                                             @RequestParam int postalCode) {
        try {
            String listingId = listingService.addListing(sellerId, productId, price, productDescription, postalCode);
            return ResponseEntity.ok("Listing added with ID: " + listingId);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error while adding listing: " + e.getMessage());
        }
    }

    @GetMapping("/{LID}")
    public ResponseEntity<Listing> getListing(@PathVariable String LID) throws Exception {
        try {
            Listing listing = listingService.getListingById(LID);
            return ResponseEntity.ok(listing);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Listing>> searchListings(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String sellerId) throws Exception {
        return ResponseEntity.ok(listingService.searchListings(brand, model, price, sellerId));
    }

    @DeleteMapping("/{LID}")
    public ResponseEntity<Void> deleteListing(@PathVariable String LID) {
        try {
            listingService.deleteListing(LID);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}


