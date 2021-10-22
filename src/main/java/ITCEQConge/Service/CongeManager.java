package ITCEQConge.Service;

import ITCEQConge.configuration.config.EmailSenderService;
import ITCEQConge.configuration.config.EmailValidator;
import ITCEQConge.entities.*;
import ITCEQConge.repository.*;
import ITCEQConge.repository.AdminRepository;
import ITCEQConge.repository.CongeRepository;
import ITCEQConge.repository.DepartementRepository;
import ITCEQConge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

@Service

public class CongeManager implements CongeManagerInterface {

    @Autowired
    CongeRepository cr ;
    @Autowired
    EmployeRepository cR ;
    @Autowired
    UserRepository ur ;
    @Autowired
    AdminRepository ar;

    @Autowired
    DepartementRepository dr ;

    @Autowired
    CongeManagerInterface CongeI ;

    @Autowired
    HistoriqueManagerInterface hi ;

    @Override// ajout conge avec date saisie
    public Conge addConge (Conge c){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        c.setDateSaisie(now);

        return cr.save(c);
    }

    @Override
    public Boolean deleteConge(Long idConge) {
        Boolean res = Boolean.FALSE;
        Conge c = cr.getByIdConge(idConge);
        if(!c.isValidationFinale()){
            cr.deleteById(idConge);
            res = Boolean.TRUE;
        }
        return res;
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
        System.out.println("username : " + username);
        Admin u = ar.findByUsernameUser(username);

        System.out.println("Admin : " + u);
        System.out.println("MatriculeBoss: " + u.getMatriculeBoss());
        Long IdDep = dr.getIdDepartmentByMatriculeBoss(u.getMatriculeBoss());

        System.out.println("idDep : " + IdDep);

        List<User> usersDep = new ArrayList<>();


            usersDep.addAll(ur.getUserByIdDep(IdDep));


        List<Conge> conges = new ArrayList<>();
        for(User userDep:usersDep){
            if (!userDep.getMatricule().equals(u.getMatriculeBoss()))
            conges.addAll(cr.getCongeByUserIdUser(userDep.getIdUser()));
        }

        Collections.reverse(conges);
        return conges ;
    }

    ////////////////////////////

    @Override
    public List<Conge> GetCongesForRemplaceur (String username){
        System.out.println("username : " + username);
       // User u = ur.findByUsernameUser(username).get();

        Long IdDep = ur.getIdDepByIdUsernameUser(username);

        Departement d = dr.findByIdDepartement(IdDep);

       // System.out.println("Admin : " + u);
       // System.out.println("MatriculeBoss: " + u.getMatriculeBoss());
       // Long IdDep = dr.getIdDepartmentByMatriculeBoss(u.getMatriculeBoss());

        System.out.println("idDep : " + IdDep);

        List<User> usersDep = new ArrayList<>();


        usersDep.addAll(ur.getUserByIdDep(IdDep));


        List<Conge> conges = new ArrayList<>();
        for(User userDep:usersDep){
            if (!userDep.getMatricule().equals(d.getMatriculeBoss()))
                conges.addAll(cr.getCongeByUserIdUser(userDep.getIdUser()));
        }

        Collections.reverse(conges);
        return conges ;
    }








    //////////////////////////////

    @Override
    public List<Conge> GetCongesForDirecGen(){

        List<Admin> admins = ar.findByRoleAdmin(Role.DEPARTMENT_BOSS);
        System.out.println("admins : "+admins);

        List<Long> ids = new ArrayList<>();
        for(Admin admin:admins){
            ids.addAll(ur.getIdUserByMatricule(admin.getMatriculeBoss()));
        }

        List<Conge> conges = new ArrayList<>();
        for(Long id:ids){
            conges.addAll(cr.getCongeByUserIdUser(id));
        }

        Collections.reverse(conges);
        return conges ;
    }

    @Override
    public List<Conge> GetCongesForSA(){

        List<Conge> conges =cr.GetCongesForSA(Boolean.TRUE);

        System.out.println("conges : "+conges);

        Collections.reverse(conges);
        return conges ;
    }

    @Override
    public List<Conge> GetCongesForEmp (String username){
        User u = ur.findByUsernameUser(username).get();
        List<Conge> conges =cr.getCongeByUserIdUser(u.getIdUser());

        Collections.reverse(conges);
        return conges ;
    }

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public void ValidationPrimaireChefDep (Long CongeId, String username){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.TRUE);

        c.setAttente(Boolean.TRUE);

        LocalDateTime now = LocalDateTime.now();
        c.setDatevalidationPrimaire(now);

        String matriculeboss = ar.findMatriculeBossByUserName(username);
        String nameDepartment = dr.getNameDepartmentByMatriculeBossUnique(matriculeboss);
        c.setMatriculeOwnerVP(matriculeboss);
        cr.save(c);



        //History
       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       // String username = auth.getName();

        String Description = "ACCEPTATION PRIMAIRE CONGE: "+ CongeId +", PAR CHEF DEP: "+ username ;

        hi.AddHistory("CONGE",username,TypeHistorique.IMPORTANT,Description);
        ////////




    }

    @Override
    public void ValidationPrimaireRemplaceur (Long CongeId, String username){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.TRUE);

        c.setAttente(Boolean.TRUE);

        LocalDateTime now = LocalDateTime.now();
        c.setDatevalidationPrimaire(now);

        String matricule = ur.findByUsernameUser(username).get().getMatricule() ;
        c.setMatriculeOwnerVP(matricule);



        cr.save(c);

        //History
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();

        String Description = "ACCEPTATION PRIMAIRE CONGE: "+ CongeId +", PAR LE REMPLACEUR: "+ username ;

        hi.AddHistory("CONGE",username,TypeHistorique.IMPORTANT,Description);
        ////////


    }

    @Override
    public void AnnuleValidationPrimaireChefDep (Long CongeId, String username, String AvisPrimaire){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.FALSE);

        c.setAttente(Boolean.TRUE);

        c.setAvisPrimaire(AvisPrimaire);

        LocalDateTime now = LocalDateTime.now();
        c.setDatevalidationPrimaire(now);

        String matriculeboss = ar.findMatriculeBossByUserName(username);
        c.setMatriculeOwnerVP(matriculeboss);
        cr.save(c);

        //History
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();

        String Description = "REFUS PRIMAIRE CONGE: "+ CongeId +", PAR CHEF DEP: "+ username ;

        hi.AddHistory("CONGE",username,TypeHistorique.IMPORTANT,Description);
        ////////

        /**mail*/

        String dataValidationFinaleString = now.toString().substring(0,10)+" "+now.toString().substring(11,19);

        Long idUser = cr.getIdUserByIdConge(CongeId);
        User User = ur.findByIdUser(idUser);
        String firstNameUser = User.getFirstNameUser();
        String lastNameUser = User.getLastNameUser();

        String UserFirstAndLastName = firstNameUser+" "+lastNameUser;
        System.out.println(UserFirstAndLastName);

        String MOwner = c.getMatriculeOwnerVP();
        System.out.println("MOwner : "+MOwner);

        Employe Owner = cR.findClientByMatricule(MOwner);
        System.out.println("Owner : "+Owner);

        String NameOwnerVP = Owner.getFirstNameUser()+" "+Owner.getLastNameUser();
        System.out.println("NameOwnerVP : "+NameOwnerVP);



        String subject = "Refus de Congé";
        emailSenderService.sendEmail(User.getEmailAddressUser(), bodyRefusPrimaire(NameOwnerVP , UserFirstAndLastName, dataValidationFinaleString) ,subject );


    }

    @Override
    public void AnnuleValidationPrimaireRemplaceur (Long CongeId, String username, String AvisPrimaire){
        Conge c = cr.findById(CongeId).get();
        c.setValidationPrimaire(Boolean.FALSE);

        c.setAttente(Boolean.TRUE);

        c.setAvisPrimaire(AvisPrimaire);

        LocalDateTime now = LocalDateTime.now();
        c.setDatevalidationPrimaire(now);

        String matricule = ur.findByUsernameUser(username).get().getMatricule() ;
        c.setMatriculeOwnerVP(matricule);
        cr.save(c);

        //History
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();

        String Description = "REFUS PRIMAIRE CONGE: "+ CongeId +", PAR REMPLACEUR: "+ username ;

        hi.AddHistory("CONGE",username,TypeHistorique.IMPORTANT,Description);
        ////////

        /**mail*/

        String dataValidationFinaleString = now.toString().substring(0,10)+" "+now.toString().substring(11,19);

        Long idUser = cr.getIdUserByIdConge(CongeId);
        User User = ur.findByIdUser(idUser);
        String firstNameUser = User.getFirstNameUser();
        String lastNameUser = User.getLastNameUser();

        String UserFirstAndLastName = firstNameUser+" "+lastNameUser;
        System.out.println(UserFirstAndLastName);

        String MOwner = c.getMatriculeOwnerVP();
        System.out.println("MOwner : "+MOwner);

        Employe Owner = cR.findClientByMatricule(MOwner);
        System.out.println("Owner : "+Owner);

        String NameOwnerVP = Owner.getFirstNameUser()+" "+Owner.getLastNameUser();
        System.out.println("NameOwnerVP : "+NameOwnerVP);



        String subject = "Refus de Congé";
        emailSenderService.sendEmail(User.getEmailAddressUser(), bodyRefusPrimaire(NameOwnerVP , UserFirstAndLastName, dataValidationFinaleString) ,subject );


    }

    public String bodyRefusPrimaire( String NameOwnerVP , String UserFirstAndLastName , String dataValidationFinaleString ){
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">ITCEQ Congé</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#4789b5\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Bonjour " + UserFirstAndLastName+  ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Votre demande de congé a été refusée par " + NameOwnerVP + ", le  "+dataValidationFinaleString  + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "  \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";}



    @Override
    public void ValidationFinale (String userNameVF,Long CongeId, String soldeThisYear , String soldeYear_1, String soldeYear_2 , String total ,  String nmbrDeJourConge ,  String RestDeJourConge, String AvisFinale, Boolean TypeValidation){
        Conge c = cr.findById(CongeId).get();

        c.setUserNameVF(userNameVF);
        c.setValidationFinale(TypeValidation);

        c.setAttenteFinale(Boolean.TRUE);
        c.setSoldeThisYear(soldeThisYear);
        c.setSoldeYear_1(soldeYear_1);
        c.setSoldeYear_2(soldeYear_2);
        c.setTotal(total);
        c.setNmbrDeJourConge(nmbrDeJourConge);
        c.setRestDeJourConge(RestDeJourConge);

        c.setAvisFinale(AvisFinale);

        LocalDateTime now = LocalDateTime.now();
        c.setDatevalidationFinale(now);

        cr.save(c);



        //History
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();
        String Description ;
        if(TypeValidation==Boolean.TRUE) {
             Description = "ACCEPTATION FINALE CONGE: " + CongeId + ", PAR SERVICE ADMINISTRATIF: " + userNameVF;
        }
        else {
             Description = "REFUS FINALE CONGE: " + CongeId + ", PAR SERVICE ADMINISTRATIF: " + userNameVF;
        }
        hi.AddHistory("CONGE",userNameVF,TypeHistorique.IMPORTANT,Description);
        ////////


        if(TypeValidation==Boolean.TRUE) {

            /**mail*/

            String dataValidationFinaleString = now.toString().substring(0,10)+" "+now.toString().substring(11,19);

            Long idUser = cr.getIdUserByIdConge(CongeId);
            User User = ur.findByIdUser(idUser);
            String firstNameUser = User.getFirstNameUser();
            String lastNameUser = User.getLastNameUser();

            String UserFirstAndLastName = firstNameUser+" "+lastNameUser;
            System.out.println(UserFirstAndLastName);

            String MOwner = c.getMatriculeOwnerVP();
            System.out.println("MOwner : "+MOwner);

            Employe Owner = cR.findClientByMatricule(MOwner);
            System.out.println("Owner : "+Owner);

            String NameOwnerVP = Owner.getFirstNameUser()+" "+Owner.getLastNameUser();
            System.out.println("NameOwnerVP : "+NameOwnerVP);

            String SAOwner = c.getUserNameVF();

            String subject = "Acceptation de Congé";
            emailSenderService.sendEmail(User.getEmailAddressUser(), bodyAcceptationFinale(SAOwner,NameOwnerVP , UserFirstAndLastName, dataValidationFinaleString) ,subject );

        }
        else {
            /**mail*/

            String dataValidationFinaleString = now.toString().substring(0,10)+" "+now.toString().substring(11,19);

            Long idUser = cr.getIdUserByIdConge(CongeId);
            User User = ur.findByIdUser(idUser);
            String firstNameUser = User.getFirstNameUser();
            String lastNameUser = User.getLastNameUser();

            String UserFirstAndLastName = firstNameUser+" "+lastNameUser;
            System.out.println(UserFirstAndLastName);

            String SAOwner = c.getUserNameVF();

            String subject = "Refus de Congé";
            emailSenderService.sendEmail(User.getEmailAddressUser(), bodyRefusFinale(SAOwner, UserFirstAndLastName, dataValidationFinaleString) ,subject );

        }

    }

    public String bodyAcceptationFinale( String SAOwner,String NameOwnerVP , String UserFirstAndLastName , String dataValidationFinaleString ){
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">ITCEQ Congé</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#4789b5\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Bonjour " + UserFirstAndLastName+  ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Votre demande de congé a été acceptée par " + NameOwnerVP +  " et le Service Administratif " +SAOwner+", le  "+dataValidationFinaleString  + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "  \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";}




    /*@Override
    public void AnnuleValidationFinale (Long CongeId, String AvisFinale){
        Conge c = cr.findById(CongeId).get();
        c.setValidationFinale(Boolean.FALSE);
        c.setAvisFinale(AvisFinale);

        c.setAttenteFinale(Boolean.TRUE);

        LocalDateTime now = LocalDateTime.now();
        c.setDatevalidationFinale(now);

        cr.save(c);

        /**mail*/
/*
        String dataValidationFinaleString = now.toString().substring(0,10)+" "+now.toString().substring(11,19);

        Long idUser = cr.getIdUserByIdConge(CongeId);
        User User = ur.findByIdUser(idUser);
        String firstNameUser = User.getFirstNameUser();
        String lastNameUser = User.getLastNameUser();

        String UserFirstAndLastName = firstNameUser+" "+lastNameUser;
        System.out.println(UserFirstAndLastName);

        String SAOwner = c.getUserNameVF();

        String subject = "Refus de Congé";
        emailSenderService.sendEmail(User.getEmailAddressUser(), bodyRefusFinale(SAOwner, UserFirstAndLastName, dataValidationFinaleString) ,subject );


    }*/

    public String bodyRefusFinale( String SAOwner, String UserFirstAndLastName , String dataValidationFinaleString ){
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">ITCEQ Congé</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#4789b5\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Bonjour " + UserFirstAndLastName+  ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Votre demande de congé a été refusée par le Service Administratif " + SAOwner + ", le  "+dataValidationFinaleString  + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "  \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";}


    @Override
    public DetailsUserConge getDetailsUserByIdConge(Long idConge){

        long idUser = cr.getIdUserByIdConge(idConge);
        Employe u = cR.findByIdUser(idUser);

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


    @Override
    public Conge getCongeByIdConge(Long idConge){
        Conge c = cr.getByIdConge(idConge);
        return c;
    }

    @Override
    public Employe getusernameUserByMatricule(String matricule){
        Employe c = cR.getClientByMatricule(matricule);

        return c;
    }

    @Override
    public Admin getusernameUserByMatriculeForDirecteur(String matricule){
        Admin a = ar.getAdminByMatricule(matricule);

        return a;
    }


    @Override
    public Boolean findRemplaceurByUserName(String username){
        Boolean test = cR.findRemplaceurByUserName(username);

        return test;
    }


}
