package ConsomiTounsi.controllers.mouadh_Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostProductModel {
    private int quantity_product;
    private String code_product;
    private int rating_product;
    private String name_product;
    private String category_product;
    private double price_product;
    private String shelf_product;
    private boolean available_product;
    private String description_product;
    private double weight_product;
}
