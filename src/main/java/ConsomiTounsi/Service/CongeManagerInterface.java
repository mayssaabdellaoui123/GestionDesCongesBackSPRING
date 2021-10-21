package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Admin;
import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Conge;
import ConsomiTounsi.entities.DetailsUserConge;

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

    void ValidationFinale (String userNameVF ,Long CongeId, String soldeThisYear , String soldeYear_1, String soldeYear_2 , String total ,  String nmbrDeJourConge ,  String RestDeJourConge, String AvisFinale, Boolean TypeValidation );

   // void AnnuleValidationFinale(Long CongeId, String username);

    public DetailsUserConge getDetailsUserByIdConge(Long idConge);
    List<Conge> GetCongesForDirecGen();
    List<Conge> GetCongesForSA();

    Boolean deleteConge(Long idConge);

    public Conge getCongeByIdConge(Long idConge);

    public Client getusernameUserByMatricule(String matricule);

    public Admin getusernameUserByMatriculeForDirecteur(String matricule);

    public Boolean findRemplaceurByUserName(String username);

    public List<Conge> GetCongesForRemplaceur (String username);




}
