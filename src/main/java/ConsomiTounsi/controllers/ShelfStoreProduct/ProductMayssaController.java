package ConsomiTounsi.controllers.ShelfStoreProduct;

import ConsomiTounsi.Service.ProductManagerInterface;
import ConsomiTounsi.Service.ShelfManagerInterface;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.entities.Shelf;
import ConsomiTounsi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductMayssaController {

    @Autowired
    ProductManagerInterface productmanager ;

    @Autowired
    ProductRepository pr;

    @PostMapping("/add")
    public void addProduct(@RequestBody Product p){ productmanager.addProduct(p);}

    @DeleteMapping("/ProductQuantityadd/{id}/{nb}")
    public void addProductQuantity(@PathVariable("id") long id , @PathVariable("nb") int nb) {
        productmanager.addProductQuantity(id , nb);
    }

    @GetMapping("/retrieve-id")
    public Product getProductById(@RequestParam("id") long id){
        return pr.findById(id).orElse(new Product());
    }

    @DeleteMapping("/ProductQuantitydec/{id}")
    public void decrementProductQuantity(@PathVariable("id") long id) {
        productmanager.decrementProductQuantity(id);
    }

    @DeleteMapping("/resetProduct/{id}")
    public void resetQuantity(@PathVariable("id") long id) {
        productmanager.resetQuantity(id);
    }

    @GetMapping("/nbProductByShelf/{id}")
    public int getNbProductsByshelf(@PathVariable("id") int id){
        return productmanager.getNbProductsByshelf(id); }


    @GetMapping("/ProductsByShelf/{id}")
    public List<Product> FindProductByIdShelf(@PathVariable("id") long id){
        return productmanager.FindProductByIdShelf(id); }


}
