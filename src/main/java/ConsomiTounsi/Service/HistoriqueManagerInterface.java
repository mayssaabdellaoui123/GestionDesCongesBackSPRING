package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;

import java.util.List;

public interface HistoriqueManagerInterface {

    public List<Historique> retrieveAllHistory();
    public long getNombreHistorique();
    public List<Historique> RetiveHistoriqueByOwner(String owner);
    public List<Historique> RetiveHistoriqueByType(TypeHistorique type);
    public List<Historique> RetiveHistoriqueByAction(String action);

    public List<Historique> RetiveHistoriqueByFiltre(TypeHistorique type, String action);
}
