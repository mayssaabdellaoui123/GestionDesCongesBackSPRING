package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Supplier;

import java.util.List;
import java.util.Optional;

public class SupplierManager implements SupplierManagerInterface{
    @Override
    public List<Supplier> retrieveAllSupplier() {
        return null;
    }

    @Override
    public Supplier addSubject(Supplier S) {
        return null;
    }

    @Override
    public void deleteSupplier(Long id) {

    }

    @Override
    public void deleteSupplier(String id) {

    }

    @Override
    public Supplier updateSupplier(Supplier S) {
        return null;
    }

    @Override
    public Optional<Supplier> FindSupplier(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Supplier> FindSupplier(String id) {
        return Optional.empty();
    }
}
