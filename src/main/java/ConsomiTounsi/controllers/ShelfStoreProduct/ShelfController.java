package ConsomiTounsi.controllers.ShelfStoreProduct;


import ConsomiTounsi.Service.ShelfManager;
import ConsomiTounsi.Service.ShelfManagerInterface;
import ConsomiTounsi.entities.Comment;
import ConsomiTounsi.entities.Shelf;
import ConsomiTounsi.entities.typeShelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelf")
@CrossOrigin(origins = "*")
public class ShelfController {
    @Autowired
    ShelfManagerInterface shelfmanager ;
    ShelfManager S;


    @GetMapping("/home")
    public String Home(){
        return ("<h1> WELCOME TO THE SHELF SERVICES </h1>");
    }

    @PostMapping("/add")
    public void addShelf(@RequestBody Shelf s){ shelfmanager.addShelf(s);}

    @GetMapping("/retrieve-all")
    public List<Shelf> getListShelves(){ return shelfmanager.retrieveAllShelf(); }

    @GetMapping("/retrieve-id")
    public Shelf getShelfById(@RequestParam("id") long id){
        return shelfmanager.FindShelf(id);
    }

    @DeleteMapping("/remove-id/{id}")
    public void removeShelfByID(@PathVariable("id")long id){
        shelfmanager.deleteShelf(id);
    }

    @PutMapping("/update")
    public Shelf updateShelf(@RequestBody Shelf p){
        return shelfmanager.updateShelf(p);
    }

    @PutMapping("/TypeShelf")
    public void FindByTypeShelf(@RequestParam("typeShelf") String TypeShelf){shelfmanager.FindByTypeShelf(TypeShelf);}

    @DeleteMapping("/ProductToShelf/{shelfId}/{productId}")
    public void AffectProductToShelf(@PathVariable("shelfId") long shelfId, @PathVariable("productId") long productId) {
        shelfmanager.AffectProductAShelf(shelfId, productId);
    }

    @PutMapping("/ProductFromShelf")
    public void DesaffectProductFromShelf(@RequestParam("shelfId") long shelfId, @RequestParam("productId") long productId) {
        shelfmanager.desaffecterProductFromShelf(shelfId, productId);
    }


    @GetMapping("/ProductsNamesByShelf")
    public List<String> getAllProductsNamesByShelf(@RequestParam("id") long shelfId){
        return shelfmanager.getAllProductsNamesByShelf(shelfId); }

    @GetMapping("/findtypebyshelf/{typeshelf}")
    public Long idshelf(@PathVariable("typeshelf") String typeshelf) { return S.idshelf(typeshelf);}

    @GetMapping("/selectIdshelfbytype/{type}")
    public long selectIdshelfbytype(@PathVariable("type") String type) {
        return S.selectIdshelfbytype(type);
    }

}
