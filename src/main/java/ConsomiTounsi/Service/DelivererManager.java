package ConsomiTounsi.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Optional;

import ConsomiTounsi.configuration.config.EmailSenderService;
import ConsomiTounsi.configuration.config.EmailValidator;
import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ConsomiTounsi.entities.Deliverer;
import ConsomiTounsi.repository.DelivererRepository;

@Service
public class DelivererManager implements DelivererManagerInterface{


	@Autowired
	DelivererRepository dr;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public DelivererManager(DelivererRepository dr, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.dr = dr;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public List<Deliverer> retrieveAllDeliverer() {
        return (List<Deliverer>) dr.findAll();
	}

	@Autowired
	EmailValidator emailValidator;

	@Autowired
	EmailSenderService emailSenderService;

	@Override
	public Deliverer addDeliverer(Deliverer D) {
		boolean isValidEmail = emailValidator.test(D.getEmailAddressUser());
			if (!isValidEmail) {
				throw new IllegalStateException("Email not valid");
			}
		double salaire = 700 ;
		D.setSalary(salaire);
		String name = D.getFirstNameUser();
		D.setNbaccessUser(0);
		String password = D.getUsernameUser() + "#619" ;
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		D.setBonusDeliverer(0);
		D.setAvailableDeliverer(true);
		D.setPasswordUser(encodedPassword);
		D.setRoleUser(UserRole.DELIVERER);
		String subject = "Account Activated";
		//emailSenderService.sendEmail(D.getEmailAddressUser(), body(name , D.getUsernameUser(), password) ,subject );
		return dr.save(D);
	}


	@Override
	public Deliverer updateDeliverer(Deliverer D) {
		if (! bCryptPasswordEncoder.matches(D.getUsernameUser() + "#619", D.getPasswordUser()))
		{ D.setUpdatedPassword(true); }
		String encodedPassword = bCryptPasswordEncoder.encode(D.getPasswordUser());
		D.setPasswordUser(encodedPassword);
		return dr.save(D);}

	@Override
	public Deliverer FindDelivererById(Long id) {
		return  dr.findById(id).orElse(new Deliverer());
	}

	@Override
	public Deliverer FindDelivererById(String id) {
		return  dr.findById(Long.parseLong(id)).orElse(new Deliverer());
	}

	@Override
	public long getNbAvailableDeliveres() {
		return dr.getNbAvailableDeliveres();
	}

	@Override
	public long getNbDeliverer() {
		return dr.getNbDeliverer();
	}

	@Override
	public Deliverer getDelivererOfTheMonth() {
		return dr.getDelivererOfTheMonth();
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
				"                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Welcome to Consomi Tounsi #619</span>\n" +
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
				"                    <td bgcolor=\"#7C0A02\" width=\"100%\" height=\"10\"></td>\n" +
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

	@Override
	public double SommeSaliareDeliverer() {
		return dr.SommeSaliareDeliverer(); }

	@Override
	public void resetBonus() {
		//%date systeme , si 01 debut de mois
		//appelé chaque auth
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		if ( now.getDayOfMonth() == 01) { dr.resetBonus();}
	}

	@Override
	public void salaireAvecPrime() {
		for (Deliverer d : (List<Deliverer>) dr.findAll()){
		double nb =0; ////= calculer prime ()
		nb = d.getSalary() + d.getBonusDeliverer();
		dr.salaireAvecPrime(nb , d.getIdUser());}
	}

	@Override
	public double updateprimeDeliverer(long id) {
		double nb = 0; 		//// nb = calculer prime ()
		return dr.UpdatePrimeDeliverer(nb,id);
	}

	@Override
	public double calculatePrime(long id) {
		return 0;
	}

	@Override
	public void electDelivererOftheMonth(){
		double nb =0 ;
		long id =0 ;
		for (Deliverer d : (List<Deliverer>) dr.findAll())
		{double b = d.getBonusDeliverer();
			if ( b>nb ){
				nb = b;
				Deliverer elected =d;
				id = elected.getIdUser();} }
		dr.updateDelivofthemonth(id);}

	@Autowired
	DelivererRepository Der;

   @Override
   public void deleteDelivererById(Long id) {
   	Der.deleteById(id);
   }

   @Override
   public void deleteDelivererById(String id) {
   	Der.deleteById(Long.parseLong(id));
   }

   @Override
   public Optional<Deliverer> FindDelivererByIdOpt(Long id) {
   	return Der.findById(id);
   }

   @Override
   public Optional<Deliverer> FindDelivererByIdOpt(String id) {
       return Der.findById(Long.parseLong(id));
   }
   
   public long FindMaxScore() {
       return Der.getMaxScore();
   }

   
   public void DelivererOfTheMonthMail(){
	   long id = Der.getMaxScore();
	   Optional <Deliverer> optionalDeliverer = Der.findById(id);
	   Deliverer deliverer = optionalDeliverer.get();
       String name = deliverer.getFirstNameUser();
       String subject = "Deliverer of the Month ";
       emailSenderService.sendEmail(deliverer.getEmailAddressUser(), body(name), subject);
   }

    public String body(String name) {
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Consomi Tounsi #619</span>\n" +
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
                "                    <td bgcolor=\"#7C0A02\" width=\"100%\" height=\"10\"></td>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> you are the deliverer of the month </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
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
}
