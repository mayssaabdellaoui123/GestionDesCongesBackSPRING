package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.ClientRepository;
import ConsomiTounsi.repository.DepartementRepository;
import ConsomiTounsi.repository.HistoriqueRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;
import ConsomiTounsi.entities.TypeHistorique;


@Service

public class DepartementManager implements DepartementManagerInterface{

    @Autowired
    DepartementRepository dr;

    @Autowired
    UserRepository ur ;

    @Autowired
    ClientRepository cr;

    @Autowired
    HistoriqueRepository hr ;

    @Autowired
    HistoriqueManagerInterface hi ;


    @Override
    public List<Departement> retrieveAllDepartment() {

        List<Departement> ld =dr.findAll() ;
        Collections.reverse(ld);
        return (ld) ;
    }

    @Override
    public Departement addDepartment ( Departement department){

        //History
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        String Description = "ADD DEPARTMENT => nom de department : "+ department.getNomDepartement()+" // Matricule Boss : "+department.getMatriculeBoss() ;

        hi.AddHistory("DEPARTMENT",username,TypeHistorique.NOT_IMPORTANT,Description);
        ////////

        return dr.save(department);
    }



    @Override
    public Departement updateDepartment(Departement dp) {
        //History
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Departement OldD = dr.findById(dp.getIdDepartement()).get();
        String Description = "UPDATE DEPARTMENT ::" ;
        if(!OldD.getNomDepartement().equals(dp.getNomDepartement())){
            Description=Description+" nom de department : "+ OldD.getNomDepartement() +" => "+ dp.getNomDepartement()+ " //";
        }
        if (!OldD.getMatriculeBoss().equals(dp.getMatriculeBoss())){
            Description=Description+" Matricule Boss : "+ OldD.getMatriculeBoss()+" => "+dp.getMatriculeBoss();
        }

        hi.AddHistory("DEPARTMENT",username,TypeHistorique.NOT_IMPORTANT,Description);
        ////////
        return dr.save(dp);
    }


    @Override
    public void deleteDepartmentById(Long id) {

        //History
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Departement department = dr.findById(id).get();
        String Description = "DELETE DEPARTMENT => nom de department : "+ department.getNomDepartement()+" // Matricule Boss : "+department.getMatriculeBoss() ;

        hi.AddHistory("DEPARTMENT",username,TypeHistorique.IMPORTANT,Description);
        ////////

        dr.deleteById(id);
    }

    public  List<String> getNameDepartmentByMatriculeBoss(String MatriculeBoss){
        return  dr.getNameDepartmentByMatriculeBoss(MatriculeBoss);

    }


    @Transactional
    public void AffectEmployeeDepartment(long DepartId, String matricule) {

        Departement DepartmentManagedEntity = dr.findById(DepartId).get();

        User clientManagedEntity = ur.findUserByMatricule(matricule).get();


        if (ObjectUtils.isEmpty(DepartmentManagedEntity)== false && !ObjectUtils.isEmpty(clientManagedEntity) )
        {clientManagedEntity.setDepartement(DepartmentManagedEntity);}

    }


    public void DesaffectEmployeeDepartment(long DepartId, String matricule) {
        User u = ur.findUserByMatricule(matricule).orElse(new User());
        Departement d = dr.findById((long) DepartId).orElse(new Departement());
        u.setDepartement(null); ur.save(u);
        d.getUsers().remove(u); dr.save(d);
    }


}
