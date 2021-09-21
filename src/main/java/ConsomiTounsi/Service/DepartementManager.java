package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.ClientRepository;
import ConsomiTounsi.repository.DepartementRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class DepartementManager implements DepartementManagerInterface{

    @Autowired
    DepartementRepository dr;

    @Autowired
    UserRepository ur ;

    @Autowired
    ClientRepository cr;

    /*@Override
    public List<Departement> retrieveAllDepartment() {
        return (List<Departement>) dr.findAll();
    }*/

    @Override
    public List<Departement> retrieveAllDepartment() {
        return (List<Departement>) dr.findAll();
    }

    @Override
    public Departement addDepartment ( Departement dp){
        return dr.save(dp);
    }

   /* public Optional<String> retrieveNameBossDepartmentByMatricule () {
        List<Departement> Ld = dr.findAll() ;
        List<Client> Lc = (List<Client>) cr.findAll();
        Optional<String> R ;

        for ( Departement dep: Ld){
            for ( Client client : Lc) {

                if()

            }
        }


    }*/


    @Override
    public Departement updateDepartment(Departement dp) {
        return dr.save(dp);
    }


    @Override
    public void deleteDepartmentById(Long id) {
        dr.deleteById(id);
    }

    public String getNameDepartmentByMatriculeBoss(String MatriculeBoss){
        return  dr.getNameDepartmentByMatriculeBoss(MatriculeBoss);

    }


}
