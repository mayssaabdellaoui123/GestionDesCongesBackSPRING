package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Shelf;
import ConsomiTounsi.entities.Store;
import ConsomiTounsi.repository.StoreRepository;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreManager implements StoreManagerInterface{
	
	 @Autowired
	    StoreRepository Str; 
    @Override
    public List<Store> retrieveAllStore() {

        return (List<Store>) Str.findAll();
    }


    @Override
    public int UpdateAdressStore(String adress, long id) {
        return Str.UpdateAdressStore(adress,id);
    }

    @Override
    public Store updateStore(Store St) {

        return Str.save(St);    }


}
