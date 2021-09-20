package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;
import ConsomiTounsi.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueManager implements HistoriqueManagerInterface{

    @Autowired
    HistoriqueRepository HR ;


    @Override
    public List<Historique> retrieveAllHistory() {
        return (List<Historique>) HR.findAll();

    }

    @Override
    public long getNombreHistorique() {
        return HR.getNombreHistorique();
    }

    @Override
    public List<Historique> RetiveHistoriqueByOwner(String owner) {
        return HR.RetiveHistoriqueByOwner(owner);
    }

    @Override
    public List<Historique> RetiveHistoriqueByType(TypeHistorique type) {
        return HR.RetiveHistoriqueByType(type);
    }

    @Override
    public List<Historique> RetiveHistoriqueByAction(String action) {
        return HR.RetiveHistoriqueByAction(action);
    }

    @Override
    public List<Historique> RetiveHistoriqueByFiltre(TypeHistorique type,String action) {
        return HR.RetiveHistoriqueByFiltre(type,action);
    }

}
