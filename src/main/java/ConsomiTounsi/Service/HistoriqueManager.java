package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
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



}
