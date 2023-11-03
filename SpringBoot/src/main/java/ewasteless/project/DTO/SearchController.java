package ewasteless.project.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ewasteless.project.classes.Product;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<List<Product>> searchProducts(@RequestBody SearchCriteria criteria) {
        List<Product> products = searchService.searchProducts(criteria);
        return ResponseEntity.ok(products);
    }
}

