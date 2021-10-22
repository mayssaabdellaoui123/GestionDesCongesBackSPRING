package ITCEQConge.Service;

import ITCEQConge.configuration.config.EmailSenderService;
import ITCEQConge.entities.*;
import ITCEQConge.repository.AdminRepository;
import ITCEQConge.configuration.config.EmailValidator;
import ITCEQConge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AdminManager implements AdminManagerInterface{

	@Autowired
    AdminRepository Ar;

    @Autowired
    UserRepository ur ;

    @Override
    public List<Admin>  retrieveAllAdmin() {
        return (List<Admin>) Ar.findAll();
    }

    @Override
    public void deleteAdminById(Long id) {
        Ar.deleteById(id);
    }

    @Override
    public void deleteAdminById(String id) {
        Ar.deleteById(Long.parseLong(id));
    }

    @Transactional
    @Override
    public Admin updateAdmin(Admin A) {
       /*if (!bCryptPasswordEncoder.matches(A.getUsernameUser() + A.getMatricule() , A.getPasswordUser()))
        {A.setUpdatedPassword(true); }*/
       /* String encodedPassword = bCryptPasswordEncoder.encode(A.getPasswordUser());
        A.setPasswordUser(encodedPassword);*/

        Optional<Admin> c = Ar.findById(A.getIdUser());
        User u=ur.findByIdUser(A.getIdUser()) ;

        if(A.getPasswordUser().equals(u.getPasswordUser())) {
            return Ar.save(A);
        }
        else {
            if (!bCryptPasswordEncoder.matches(A.getUsernameUser() + A.getMatricule() , A.getPasswordUser()))
            {A.setUpdatedPassword(true);
            String encodedPassword = bCryptPasswordEncoder.encode(A.getPasswordUser());
            A.setPasswordUser(encodedPassword);
             }
            return Ar.save(A);
        }

        }

    @Override
    public Admin FindAdminById(Long id) {
        return  Ar.findById(id).orElse(new Admin());
    }

    @Override
    public Admin FindAdminById(String id) {
        return  Ar.findById(Long.parseLong(id)).orElse(new Admin());
    }
    
    public List<Admin> FindAdminByRole(Role role){
    	return Ar.findByRoleAdmin(role);
    }

	@Override
	public long getNbAdminByRole(Role role) {
		return Ar.getNbAdminByRole(role);
	}

	@Override
	public long getNbAdmin() {
		return Ar.getNbAdmin();
	}

	//registration

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminManager(AdminRepository ar, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Ar = ar;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public Admin AddAdmin(Admin user){
        boolean isValidEmail = emailValidator.test(user.getEmailAddressUser());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        double salaire = 1000 ;
        user.setSalary(salaire);
        user.setNbabsenceAdmin(0);
        user.setNbaccessUser(0);
        String name = user.getFirstNameUser();
        String password = user.getUsernameUser() + "itceq"  ;
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPasswordUser(encodedPassword);
        user.setRoleUser(UserRole.ADMIN);
        String subject = "Account Activated";
        emailSenderService.sendEmail(user.getEmailAddressUser(), body(name , user.getUsernameUser(), password) ,subject );
        Ar.save(user);

        //User a = ur.findUserByMatricule(user.getMatriculeBoss()).get();
        user.setMatricule(user.getMatriculeBoss());

        return user;
    }

    @Override
    public void resetAbsence(int nb) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if ( now.getDayOfMonth() == 01) { Ar.resetAbsence(0);}
    }

    @Override
    public int addAbsence(long id) {
        Admin a = Ar.findById(id).orElse(new Admin());
        int nb = a.getNbabsenceAdmin() + 1;
        return Ar.AddAbsence(nb , id);
    }

    public String body( String name , String username , String password){
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Welcome to ITCEQ Conge</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> The following are the default credentials you can use to log in to your account that you can change later on <br> Username : " + username + " <br> Password : " +password+ "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
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

}
