package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Conge;

import java.util.List;

public interface CongeManagerInterface {

    Conge addConge (Conge c);
    void AffectEmployeConge(long CongeId, String matricule);

    void addCongeEtAffectation (Conge c , String userName) ;

    List<Conge> GetCongesForChefDep (String username);
    List<Conge> GetCongesForEmp (String username);

    void ValidationPrimaireChefDep (Long CongeId, String username);
    void AnnuleValidationPrimaireChefDep (Long CongeId, String username , String AvisPrimaire);

    void ValidationPrimaireRemplaceur (Long CongeId, String username);
    void AnnuleValidationPrimaireRemplaceur (Long CongeId, String username, String AvisPrimaire);

    void ValidationFinale(Long CongeId);
    void AnnuleValidationFinale(Long CongeId, String username);

    List<Conge> GetCongesForDirecGen();




}
