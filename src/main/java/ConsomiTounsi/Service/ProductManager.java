package ConsomiTounsi.Service;

import ConsomiTounsi.configuration.config.EmailSenderService;
import ConsomiTounsi.entities.*;
import ConsomiTounsi.repository.FeedbackRepository;
import ConsomiTounsi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
    public class ProductManager implements ProductManagerInterface{

	@Autowired
    AdminManagerInterface adminS;

	@Autowired
    ProductRepository Pr;


    @Autowired
    ClaimManager claimManager;


    private EntityManager em;

    @Override
	public EntityManager getEntityManager() {
        return em;
        
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return (List<Product>) Pr.findAll();
    }

    @Override
    public Product addProduct(Product P) {
       /* Product optionalproduct = Pr.save(P);
        long id = P.getId_product();
        addProductQuantity(id);*/

        return Pr.save(P);
    }
    
    

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = Pr.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new IllegalStateException("Product Not Found");
        }
        Pr.deleteById(id);

    }

    @Override
    public void deleteProduct(String id) {


        Pr.deleteById(Long.parseLong(id));
    }

    @Override
    public Product updateProduct( Product P) {

        return Pr.save(P);
    }

    @Override
    public Optional<Product> FindProduct(Long id) {
        return Pr.findById(id);
    }

    @Override
    public Optional<Product> FindProduct(String id) {
        return Pr.findById(Long.parseLong(id));
    }

    @Override
    public List<Product> FindProductByIdShelf(Long id) {
        return this.Pr.FindProductByIdShelf(id);
    }

   /* @Override
   	public List<Product> FindProductByIdStock(Long id) {
   		return this.Pr.FindProductByIdStock(id);
   	}*/

    public boolean product_exist(Long id) {
        Optional<Product> p = this.Pr.findById(id);
        return p.isPresent();
    }


    //find By Name
    public List<Product> findByName(String name) {
        return Pr.findProductByName(name);
    }

    // find By Rating
    public List<Product> findByRating() {
        return Pr.findAllByOrderByRatingDesc();
    }

    // find All Product by desc Order
    public List<Product> findByDescPrice() {
        return Pr.findAllByOrderByPriceDesc();
    }

    // find All Product By Asc Order
    public List<Product> findByAscPrice() {
        return Pr.findAllByOrderByPriceAsc();
    }

    //find Product By Category
    public List<Product> findProductByCategory(String name) {
        return Pr.findProductByCategory(name);
    }

    /*
    public List<Product> FindByOrder(Long id) {
    return this.Pr.FindByOrder(id);
    } */

    // Service that send Auto Claim if the the Rating is less than 2.0

    public void sendAutoReclamation(List<Product> productList) {

        for (Product product : productList) {
            Product optionalProduct = Pr.findById(product.getId()).get();
            Feedback feedback = optionalProduct.getFeedback();
            float nb = feedback.getNote();
                if (nb <= 2) {

                    Claim claim = new Claim();
                    claim.setDecision("Not Yet");
                    claim.setDescription("my mood is moody");
                    claim.setLevel(99);
                    this.claimManager.addClaim(claim);



            }
        }
    }

   // make Worst Product unavailable

    public void makeUnavailible(){
        List<Long> list=claimManager.WorstProducts();
        for (Long id: list){
            Optional<Product> optionalProduct=Pr.findById(id);
            if (optionalProduct.isPresent()){
                Product product=optionalProduct.get();
                product.setAvailable(false);
                Pr.save(product);

            }

        }

    }
    @Autowired
    FeedbackRepository feedbackRepository;

    // make product of the months
    public void makeProductOfTheMonth(){
        List<Feedback>list=feedbackRepository.findTop10ByNote(4);
        for (Feedback feedback:list){
            Product product =feedback.getProduct();
            product.setProductofthemonth(true);
        }

    }


    @Override
    public int getNbProductsByshelf(int idshelf) {
        return Pr.getNbProductsByshelf(idshelf);   }

    @Override
    public int addProductQuantity(long id , int nb ) {
        Product p = Pr.findById(id).orElse(new Product());
        long qn = p.getQuantityProduct() + nb ;
        return Pr.AddQuantity(qn , id);
    }

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public int decrementProductQuantity(long id) {
        Product p = Pr.findById(id).orElse(new Product());
        String product = p.getName();
            long qn = p.getQuantityProduct() - 1;
           if (qn<0){qn=0;}
        List<Admin>  list = adminS.retrieveAllAdmin();
        if ( qn < 10 )  {
            for (Admin a : list) {
                Role role = a.getRoleAdmin();
                if (role.equals(Role.PRODUCTS_MANAGER)) {
                    String name=a.getFirstNameUser();
                    String subject = "Quantity almost done";
                    emailSenderService.sendEmail(a.getEmailAddressUser(),body(name , product) ,subject );}
            }
        }
        return Pr.AddQuantity(qn , id);
    }

    public String body( String name , String product){
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> the product " + product + "'s quantity is less than 10 items , it's about time to contact the supplier</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
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
    public void resetQuantity(long id) {
        Pr.resetQuantity(id);
    }

    ///// it's the part of her majesty, her highness,mahou moutourat
    
    
    
   /* @Override
	public List<Product> findByCategoryProduct(TypeCategory cp)
    {
    	
    	
    	
		return Pr.findByCategoryProduct(cp);*/
		
		
		
		
		/*@Override
		public void showAdvertsement(TypeCriteria criteria) {
		
		if ( criteria == TypeCriteria.ALL) 
			
			
		{ 	Iterable<Product> products = Pr.findAll() ;
        for (int i=0;i<products.size();i++){

            Product p=products.get(i);
        }
            
		}*/
            
            
		
			
			
			/*{ for(Product product : products)
		printMessage("-------------------------------------------------------------");
        Product product;
		printMessage("Check this product " + product.getNameProduct() );
        printMessage("It only costs " + product.getPriceProduct()  );
   
        printMessage("-------------------------------------------------------------");
			}
    } */
		 
		/*{ return (List<Product>) Pr.findAll();}
		else 
		if ( criteria == TypeCriteria.GENDER)
			{
			if (criteriaValue1== TypeCriteriaValue1.MALE)
			
				{return }
			else if (criteriaValue2== TypeCriteriaValue2.FEMALE)
				{return}
			}
			
		else 
		if ( criteria == TypeCriteria.WorkField)
			
		}*/
	
    


	//private void printMessage(String message) {
   // System.out.println(message);


	
		
		


//send List of id for her majesty the Nooba maha hail to the king Mouadh
    @Override
    public List<Long> sendIdClaimedProduct(){
        List<Long> lass3ed=new ArrayList<Long>();
        for ( Product product : Pr.findAll() ){
            Feedback feedback= product.getFeedback() ;
                float nb=feedback.getNote();
                if (nb<=2){
                    Long id= product.getId() ;
                    lass3ed.add(id);
                }
            }
        return lass3ed;
}
    
}
