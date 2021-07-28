package ConsomiTounsi.API.Stripe;

import ConsomiTounsi.Service.PaymentService;
import ConsomiTounsi.entities.Order;
import ConsomiTounsi.entities.Payment;
import ConsomiTounsi.entities.Payment_type;
import ConsomiTounsi.pdf.PdfService;
import ConsomiTounsi.repository.PaymentRepository;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class StripeController {
  @Autowired
    PaymentRepository paymentRepository;
  @Autowired
    PaymentService paymentService;
  @Autowired
    PdfService pdfService;


    // create a Gson object
    private static Gson gson = new Gson();

    @PostMapping("/payment")
    /**
     * Payment with Stripe checkout page
     *
     * @throws StripeException
     */
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException, MessagingException {
        // We initilize stripe object with the api key
        init();
        //email
        Payment payment1 = new Payment();
        payment1.setDate(new Date());
        payment1.setAmount(payment.getAmount());
        payment1.setDescription("Your Payment is Successful");
        payment1.setEmail(payment.getEmail());
        Order order1 =new Order();
        order1.setId(1L);
        order1.setPaid(true);
        order1.setPaymentType(Payment_type.online);
        payment1.setOrder(order1);
        paymentRepository.save(payment1);
        paymentService.sendWithAttachment(payment1,pdfService.toPDF(payment1.getId()));
        // We create a  stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
        // create a stripe session
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we putted inside the response data you can get more info from the session object
        responseData.put("id", session.getId());
        // We can return only the sessionId as a String
        return gson.toJson(responseData);
    }

    private static void init() {
        Stripe.apiKey = "sk_test_51IYg5mIdCoFRZnwK1Bnv6AXVy9qWLOptnVllvv1kegibJeHsGU5y5M7zWVvGZjzujy7WIsFhrdzPhpmysAdnCfbU00pjfiLJC6" ;
    }
}
