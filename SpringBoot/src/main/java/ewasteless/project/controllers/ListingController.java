package ewasteless.project.controllers;

// Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Java imports
import java.util.List;
import java.util.concurrent.ExecutionException;

// Your application's model imports
import ewasteless.project.classes.Listing;
import ewasteless.project.service.ListingService;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @PostMapping
    public ResponseEntity<String> addListing(@RequestBody Listing listing) {   
        try {
            String listingId = listingService.addListing(listing);
            return new ResponseEntity<>(listingId, HttpStatus.CREATED); 
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   

    @GetMapping("/{listingId}")
    public ResponseEntity<Listing> getListing(@PathVariable String listingId) {
        try {
            Listing listing = listingService.getListingById(listingId);
            return new ResponseEntity<>(listing, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
}


