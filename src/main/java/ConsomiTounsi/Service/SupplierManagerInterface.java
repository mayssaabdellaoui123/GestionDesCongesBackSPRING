package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierManagerInterface {
    List<Supplier> retrieveAllSupplier();
    Supplier addSubject(Supplier S);
    void deleteSupplier(Long id);
    void deleteSupplier(String id);
    Supplier updateSupplier(Supplier S);
    Optional<Supplier> FindSupplier(Long id);
    Optional<Supplier> FindSupplier(String id);
}
