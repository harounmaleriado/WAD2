package ewasteless.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ewasteless.project.classes.Product;
import ewasteless.project.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable String productId) {
        try {
            Product product = productService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductsByCriteria(
            @RequestParam String type,
            @RequestParam Optional<String> model,
            @RequestParam Optional<String> brand,
            @RequestParam Optional<Double> benchmark) {
        try {
            List<Product> products = productService.getProductsByCriteria(type, model, brand, benchmark);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}


