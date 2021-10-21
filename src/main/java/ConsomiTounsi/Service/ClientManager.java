package ConsomiTounsi.Service;


import ConsomiTounsi.configuration.config.EmailSenderService;
import ConsomiTounsi.entities.*;


import ConsomiTounsi.entities.Client;

import ConsomiTounsi.repository.AdminRepository;
import ConsomiTounsi.repository.ClientRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import ConsomiTounsi.configuration.config.EmailValidator;
import ConsomiTounsi.repository.DepartementRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ClientManager implements ClientManagerInterface{

	@Autowired
	ClientRepository cr;

	@Autowired
	UserRepository ur ;

	@Autowired
	UserManagerInterface um;

	@Autowired
	DepartementRepository dr;

	@Autowired
	AdminRepository ar;

	@Override
	public Client AffectationRemplaceur(String Matricule) {
		Client c = cr.findClientByMatricule(Matricule);
		c.setRemplaceur(Boolean.TRUE);
		cr.save(c);
		return(c);
	}

	@Override
	public Client DesAffectationRemplaceur(String Matricule) {
		Client c = cr.findClientByMatricule(Matricule);
		c.setRemplaceur(Boolean.FALSE);
		cr.save(c);
		return(c);
	}



	@Override
	public List<Client> retrieveAllClient() {
		return (List<Client>) cr.findAll();

	}

	@Override
	public List<User> retrieveAllEmployees() {

		return um.findUserByRole(UserRole.CLIENT);
	}

	@Override
	public void deleteClientById(Long id) {
        cr.deleteById(id);
	}

	@Override
	public void deleteClientById(String id) {
        cr.deleteById(Long.parseLong(id));		
	}

	@Override
	public Client updateClient(Client Cl) {
		/*String encodedPassword = bCryptPasswordEncoder.encode(Cl.getPasswordUser());
		Cl.setPasswordUser(encodedPassword);*/

		Optional<Client> c = cr.findById(Cl.getIdUser());
		User u=ur.findByIdUser(Cl.getIdUser()) ;

		if(Cl.getPasswordUser().equals(u.getPasswordUser())) {
			return cr.save(Cl);
		}
		else {
			String encodedPassword = bCryptPasswordEncoder.encode(Cl.getPasswordUser());
			Cl.setPasswordUser(encodedPassword);
			return cr.save(Cl);
		}
	}

	@Override
	public Client FindClientById(long id) {
		return  cr.findById(id).orElse(new Client());
	}

	@Override
	public Client FindClientById(String id) {
		return  cr.findById(Long.parseLong(id)).orElse(new Client());}

	@Override
	public long getNombreClient() {
		return cr.getNombreClient();
	}

	//registration

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public ClientManager(ClientRepository cr, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.cr = cr;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Autowired
	EmailValidator emailValidator;

	@Autowired
	EmailSenderService emailSenderService;

	public String body( String name){
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
				"            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Our Team Hexacode hopes it will be a good experience. </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
				"  \n" +
				"      </td>\n" +
				"      <td width=\"10\" valign=\"middle\"><br></td>\n" +
				"    </tr>\n" +
				"    <tr>\n" +
				"      <td height=\"30\"><br></td>\n" +
				"    </tr>\n" +
				"  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
				"\n" +
				"</div></div>";

	}

	@Override
	public Client SignUpClient(@Valid Client user) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		user.setSubscriptionDate(now);
		user.setSubMonth(now.getMonth().toString());
		user.setNbaccessUser(0);
		user.setUpdatedPassword(true);
		String password = user.getPasswordUser();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		user.setPasswordUser(encodedPassword);
		user.setRoleUser(UserRole.CLIENT);
		String name=user.getFirstNameUser();
		String subject = "ITCEQ Registration";
		emailSenderService.sendEmail(user.getEmailAddressUser(),body(name) ,subject );
		return cr.save(user);
	}

	@Override
	public long getNBClientsbysubmonth(String Month) {
		return cr.getClientsbysubmonth(Month);
	}

	@Override
	public Client addClient(Client Cl) {

		return cr.save(Cl);
	}

	public List<User> retriveRemplaceur( String username ) {

		System.out.println("username :" + username );

		List<Long> ListIdUsers = cr.getRemplaceurIdUser(Boolean.TRUE);

		System.out.println("ListIdUsers :" + ListIdUsers );

		Admin a = ar.findByUsernameUser(username);

		System.out.println("chef :" + a );

		List<User> listremplaceur = new ArrayList<>();


		String matricule = a.getMatriculeBoss();

		System.out.println("matricule :" + matricule );

		long iddep = dr.getIdDepartmentByMatriculeBoss(matricule);


		for ( long id: ListIdUsers ) {

			System.out.println("id :" + id );
			User u = ur.findByIdUser(id);
			System.out.println("u :" + u );

			System.out.println("u.getDepartement() 9bal :" + u.getDepartement() );
			System.out.println("u.getDepartement().getIdDepartement() 9bal :" + u.getDepartement().getIdDepartement() );
			if(u.getDepartement().getIdDepartement()==iddep){
				System.out.println("u.getDepartement() ba3ed:" + u.getDepartement().getIdDepartement() );

				System.out.println("listremplaceur :" + listremplaceur );
				listremplaceur.add(u);
			}
		}

		System.out.println("listremplaceur :" + listremplaceur );
		return listremplaceur;

		//List<Client> ListClients = cr.RetiveRemplaceur(Boolean.TRUE);
		/*

		System.out.println("matricule :" + matricule );
		long iddep = dr.getIdDepartmentByMatriculeBoss(matricule);

		System.out.println("iddep :" + iddep );

		List<User> listUserdep = ur.getUserByIdDep(iddep);

		System.out.println("listUserdep :" + listUserdep );

		List<User> listremplaceur = new ArrayList<>();

		for ( User i: listUserdep ) {
			/*System.out.println("i :" + i.getIdUser() );
			long id=i.getIdUser();

			System.out.println("mou3a  :" + id );
		    Client client = cr.findByIdUser(id);

			System.out.println("client :" + client );*/

		/*	if ( (cr.findByIdUser(i.getIdUser()).getRemplaceur()) == Boolean.TRUE){
				listremplaceur.add(i);
			}

			System.out.println("listeremplaceur :" + listremplaceur );

		}

		System.out.println("listeremplaceur :" + listremplaceur );

		return listremplaceur;
		*/

	}

	@Override
	public String getUserNameFromMatricule(String matricule) {

		Client c = cr.getClientByMatricule(matricule);

		return c.getUsernameUser();
	}


}
