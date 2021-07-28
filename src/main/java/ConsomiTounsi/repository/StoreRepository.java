package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Store;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store,Long> {

    @Modifying
    @Query(value="UPDATE Store s SET s.address_store=:adress WHERE s.id_store=:id",nativeQuery= true)
    int UpdateAdressStore(@Param("adress") String adress , @Param("id") long id);
}
