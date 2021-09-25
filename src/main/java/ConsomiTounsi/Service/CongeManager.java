package ConsomiTounsi.Service;

import ConsomiTounsi.configuration.security.UserDetails;
import ConsomiTounsi.entities.*;
import ConsomiTounsi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
@Service

public class CongeManager implements CongeManagerInterface {

    @Autowired
    CongeRepository cr ;
    @Autowired
    ClientRepository cR ;
    @Autowired
    UserRepository ur ;
    @Autowired
    AdminRepository ar;

    @Autowired
    DepartementRepository dr ;

    @Autowired
    CongeManagerInterface CongeI ;

    @Override// ajout conge avec date saisie
    public Conge addConge (Conge c){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        c.setDateSaisie(now);

        return cr.save(c);
    }

   @Override
    public void addCongeEtAffectation (Conge c, String username){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        c.setDateSaisie(now);

        String matricule = ur.findByUsernameUser(username).get().getMatricule();

        CongeI.AffectEmployeConge(cr.save(c).getIdConge(),matricule) ;

    }




    @Transactional
    public void AffectEmployeConge(long CongeId, String matricule) {
        Conge CongeManagedEntity = cr.findById(CongeId).get();
        User clientManagedEntity = ur.findUserByMatricule(matricule).get();

        if (ObjectUtils.isEmpty(CongeManagedEntity)== false && !ObjectUtils.isEmpty(clientManagedEntity) )
        {CongeManagedEntity.setUsers(clientManagedEntity);}


    }

    @Override
    public List<Conge> GetCongesForChefDep (String username){
        User u = ur.findByUsernameUser(username).get();
        List<Long> IdDep = dr.getIdDepartmentByMatriculeBoss(u.getMatricule());

        List<User> usersDep = new ArrayList<>();

        for(Long id: IdDep){
            usersDep.addAll(ur.getUserByIdDep(id));
        }

        List<Conge> conges = new ArrayList<>();
        for(User userDep:usersDep){
            conges.addAll(cr.getCongeByUserIdUser(userDep.getIdUser()));
        }
        return conges ;
    }

    @Override
    public List<Conge> GetCongesForEmp (String username){
        User u = ur.findByUsernameUser(username).get();
        List<Conge> conges =cr.getCongeByUserIdUser(u.getIdUser());

        return conges ;
    }



    @Override
    public void ValidationPrimaireChefDep (Long CongeId, String username){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.TRUE);

        String matriculeboss = ar.findMatriculeBossByUserName(username);
        c.setMatriculeOwnerVP(matriculeboss);
        cr.save(c);
    }

    @Override
    public void ValidationPrimaireRemplaceur (Long CongeId, String username){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.TRUE);

        String matricule = ur.findByUsernameUser(username).get().getMatricule() ;
        c.setMatriculeOwnerVP(matricule);
        cr.save(c);
    }

    @Override
    public void AnnuleValidationPrimaireChefDep (Long CongeId, String username, String AvisPrimaire){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.FALSE);

        c.setAvisPrimaire(AvisPrimaire);

        String matriculeboss = ar.findMatriculeBossByUserName(username);
        c.setMatriculeOwnerVP(matriculeboss);
        cr.save(c);
    }

    @Override
    public void AnnuleValidationPrimaireRemplaceur (Long CongeId, String username, String AvisPrimaire){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.FALSE);

        c.setAvisPrimaire(AvisPrimaire);

        String matricule = ur.findByUsernameUser(username).get().getMatricule() ;
        c.setMatriculeOwnerVP(matricule);
        cr.save(c);
    }

    @Override
    public void ValidationFinale (Long CongeId ){
        Conge c = cr.findById(CongeId).get();
        c.setValidationFinale(Boolean.TRUE);

        cr.save(c);
    }

    @Override
    public void AnnuleValidationFinale (Long CongeId, String AvisFinale){
        Conge c = cr.findById(CongeId).get();
        c.setValidationFinale(Boolean.FALSE);
        c.setAvisFinale(AvisFinale);

        cr.save(c);

    }


    @Override
    public DetailsUserConge getDetailsUserByIdConge(Long idConge){

        long idUser = cr.getIdUserByIdConge(idConge);
        Client u = cR.findByIdUser(idUser);

        Departement d = u.getDepartement();

        DetailsUserConge user = new DetailsUserConge();



        user.setFirstname(u.getFirstNameUser());
        user.setLastname(u.getLastNameUser());
        user.setMatricule(u.getMatricule());
        user.setPhone(u.getPhoneNumberUser());
        user.setTache(u.getWorkfieldClient());
        user.setMatriculeBossdep(d.getMatriculeBoss());
        user.setMatriculeRemplaceur(d.getMatriculeRemplaceur());
        user.setDepartment(d.getNomDepartement());

        return user;

    }

}
