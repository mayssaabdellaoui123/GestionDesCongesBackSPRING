package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Admin;
import ConsomiTounsi.entities.Pool;
import ConsomiTounsi.repository.PoolRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PoolManager implements PoolManagerInterface{
	
	 @Autowired
	    PoolRepository Poolr; 
	 
    @Override
    public List<Pool> retrieveAllPool() {
        return (List<Pool>) Poolr.findAll();
    }

    @Override
    public Pool addPool(Pool Po) {
        return Poolr.save(Po);
    }

    @Override
    public void deletePool(Long id) {
        Poolr.deleteById(id);
    }

    @Override
    public void deletePool(String id) {
        Poolr.deleteById(Long.parseLong(id));
    }

    @Override
    public Pool updatePool(Pool Po) {
        return Poolr.save(Po) ;
    }

    @Override
    public Pool FindPoolById(Long id) {
        return Poolr.findById(id).orElse(new Pool());
    }

    @Override
    public Pool FindPoolById(String id) {
        return  Poolr.findById(Long.parseLong(id)).orElse(new Pool());
    }

    public void AddamountTopool (float amount) {
        Pool p = Poolr.findById(Long.valueOf(1)).get();
        p.setAmount_pool(p.getAmount_pool()+amount);
        System.out.print(p.getAmount_pool());
        Poolr.save(p);
    }
}
