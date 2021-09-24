package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Conge;

public interface CongeManagerInterface {

    Conge addConge (Conge c);
    void addCongeEtAffectation (Conge c , String matricule);
    public void AffectEmployeConge(long CongeId, String matricule);
}
