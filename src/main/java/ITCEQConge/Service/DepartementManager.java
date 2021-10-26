package ITCEQConge.Service;

import ITCEQConge.entities.*;
import ITCEQConge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;


@Service

public class DepartementManager implements DepartementManagerInterface{

    @Autowired
    DepartementRepository dr;

    @Autowired
    AdminRepository ar;

    @Autowired
    UserRepository ur ;

    @Autowired
    EmployeRepository cr;

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


        User u = ur.findUserByMatricule(dp.getMatriculeBoss()).get();
        String fullname = u.getFirstNameUser()+" "+u.getLastNameUser();
        dp.setNomBoss(fullname);

        Description=Description+" Matricule Boss : "+" => "+dp.getMatriculeBoss();

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

    @Override
    public  String getNameDepartmentByMatriculeBoss(String MatriculeBoss){
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


    @Override
    public Departement retrieveNameDepartmentByUsername (String username){
      Long idDep = ur.retrieveiddepByUsername(username);
      System.out.println("iddep "+idDep);


      Departement d = dr.findByIdDepartement(idDep);

      return d;

    }

    @Override
    public Departement retrieveNameDepartmentByUsernameChef (String username){

        Long iduser = ur.retrieveIdUserByUsername(username);

        System.out.println("iduser:" + iduser);

        Admin a = ar.findAdminByIduser(iduser);

        System.out.println("admin "+a);

        Departement d = dr.findByMatriculeBoss(a.getMatriculeBoss());

        return d;


    }



}