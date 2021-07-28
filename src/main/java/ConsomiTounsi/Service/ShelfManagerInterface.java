package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Admin;
import ConsomiTounsi.entities.Role;
import ConsomiTounsi.entities.Shelf;
import ConsomiTounsi.entities.typeShelf;

import java.util.List;
import java.util.Optional;

public interface ShelfManagerInterface {
    List<Shelf> retrieveAllShelf();
    Shelf addShelf(Shelf Sh);
    void deleteShelf(Long id);
    void deleteShelf(String id);
    Shelf updateShelf(Shelf Sh);
    Shelf FindShelf(Long id);
    Shelf FindShelf(String id);
    Shelf FindByTypeShelf(String type);
    void AffectProductAShelf(long shelfId, long productId);
    List<String> getAllProductsNamesByShelf(long shelfId);
    void desaffecterProductFromShelf(long shelfId, long productId);
    long selectIdshelfbytype(String type);



    }
