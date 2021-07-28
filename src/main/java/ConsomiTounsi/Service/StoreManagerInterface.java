package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Store;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StoreManagerInterface {
    List<Store> retrieveAllStore();
    Store updateStore(Store St);

    @Transactional
    int UpdateAdressStore(String adress ,long id);
}
