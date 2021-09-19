package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Historique;

import java.util.List;

public interface HistoriqueManagerInterface {

    public List<Historique> retrieveAllHistory();
    public long getNombreHistorique();
}
