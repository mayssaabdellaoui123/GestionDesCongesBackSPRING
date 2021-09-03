package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Departement;

import java.util.List;

public interface DepartementManagerInterface {

    public List<Departement> retrieveAllDepartment();
    public Departement addDepartment ( Departement dp);

}
