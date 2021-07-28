package ConsomiTounsi.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ConsomiTounsi.config.EmailSenderService1;
import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.entities.Promotion;
import ConsomiTounsi.repository.ProductRepository;
import ConsomiTounsi.repository.PromotionRepository;


@Service
public class PromotionManager implements PromotionManagerInterface  {
	
	@Autowired 
	PromotionRepository Prom;
	@Autowired 
	PromotionManager Pm;
	@Autowired 
	ProductRepository Pr;
	@Autowired 
	ClientManager Cl;

	@Override
    public List<Promotion> retrieveAllPromotion() {
    	return (List<Promotion>) Prom.findAll();
    }
    
    @Override
    public Promotion addPromotion(Promotion Pm) {
    	return Prom.save(Pm);
    }

    @Override
    public void deletePromotion(Long id) {
    	Prom.deleteById(id);
    }

    @Override
    public void deletePromotion(String id) {
    	Prom.deleteById(Long.parseLong(id));
    }

    @Override
    public Promotion updatePromotion(Promotion Pm) {
    	return Prom.save(Pm);
    }

    @Override
    public Optional<Promotion> FindPromotion(Long id) {
    	return Prom.findById(id);
    }

    @Override
    public Optional<Promotion> FindPromotion(String id) {
    	return Prom.findById(Long.parseLong(id));
    }
    
    @Autowired
    EmailSenderService1 emailSenderService;
   
   public void PromotionNotificationandPriceReduction(long PID){
	   List<Promotion> list2 = Pm.retrieveAllPromotion();
	 for (Promotion p: list2){
	     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		 SimpleDateFormat localDateFormat= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   LocalDateTime now = LocalDateTime.now();
         LocalDateTime dateProm = p.getDate_promotion_debut();
		  // String d1=localDateFormat.format(now);
		   //String d2=localDateFormat.format(dateProm);
          if(now.compareTo(dateProm)>0){
	      Product pr = Pr.findById(PID).get();
	   double reduction=p.getValeur_promotion();
	   double old_price= pr.getPrice(); 
	   double new_price;
	   new_price=(old_price*reduction)/100;
	   pr.setPrice(new_price);
	   Pr.save(pr);
	   List<Client> list = Cl.retrieveAllClient(); 

       for (Client c: list) {
	   String namep = pr.getName();
       String name = c.getFirstNameUser();
       String type= p.getType_promotion();
       double val= p.getValeur_promotion();
       String subject = "Don't miss today's promotion!";
       emailSenderService.sendEmail(c.getEmailAddressUser(),body(name,namep,type,val), subject);
       System.out.println("test");
	   }
   }
   else System.out.println("Sorry! Wrong date, there's no promotions today");
	 }
   }
   public String body(String name, String namep, String type, double val) {
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
               "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hello, our dear client " +name+ ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> ConsoumiTounsi Family wishes you and your loved ones a happy " +type+ " and that we are glad to inform you that we have a promotion on our product" +namep+ " with a  " +val+ " %, Don't miss the chance!,</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
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


