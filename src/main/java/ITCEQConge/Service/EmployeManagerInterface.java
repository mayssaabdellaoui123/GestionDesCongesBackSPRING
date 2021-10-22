package ITCEQConge.Service;


import ITCEQConge.entities.Employe;
import ITCEQConge.entities.User;

import java.util.List;

public interface EmployeManagerInterface {
	
    List<Employe> retrieveAllClient();
    void deleteClientById(Long id);
    void deleteClientById(String id);

    Employe updateClient(Employe Cl);
    Employe FindClientById(long id);
    Employe FindClientById(String id);
    long getNombreClient();
    Employe SignUpClient(Employe user);
    long getNBClientsbysubmonth(String Month);
    List<User> retrieveAllEmployees();
    Employe addClient(Employe Cl);
    Employe AffectationRemplaceur(String Matricule);
    Employe DesAffectationRemplaceur(String Matricule);

    String getUserNameFromMatricule(String matricule);

    List<User> getEmployeeByDepartment (String username);

}
