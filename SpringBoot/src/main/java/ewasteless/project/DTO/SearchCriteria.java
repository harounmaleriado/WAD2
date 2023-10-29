package ewasteless.project.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    private String brand;
    private String model;
    private Double minPrice;
    private Double maxPrice;
    private String sellerUID;
    // ... any other search criteria

    // getters and setters
}

