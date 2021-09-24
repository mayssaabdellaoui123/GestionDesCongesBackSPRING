package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Conge;

public interface CongeManagerInterface {

    Conge addConge (Conge c);
    void AffectEmployeConge(long CongeId, String matricule);

    void addCongeEtAffectation (Conge c , String userName) ;

}
