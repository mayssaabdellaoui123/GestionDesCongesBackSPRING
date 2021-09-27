package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Departement;

import java.util.List;

public interface DepartementManagerInterface {

    public List<Departement> retrieveAllDepartment();
    public Departement addDepartment ( Departement dp);
    public Departement updateDepartment(Departement dp) ;
    public void deleteDepartmentById(Long id);
    public  String getNameDepartmentByMatriculeBoss(String MatriculeBoss);
    public Departement retrieveNameDepartmentByUsername (String username);
    Departement retrieveNameDepartmentByUsernameChef (String username);


}
