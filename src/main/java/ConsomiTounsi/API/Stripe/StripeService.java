package ConsomiTounsi.API.Stripe;

import ConsomiTounsi.Service.PaymentService;
import ConsomiTounsi.entities.Order;
import ConsomiTounsi.entities.Payment;
import ConsomiTounsi.entities.Payment_type;
import ConsomiTounsi.pdf.PdfService;
import ConsomiTounsi.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Autowired
    PaymentService paymentService ;
    @Autowired
    PaymentRepository paymentRepository ;
    @Autowired
    PdfService pdfService;
    @Value("${stripe.key.secret}")
    private String API_SECET_KEY;

    public StripeService() {

    }

    public String createCharge(String email, String token, int amount) {

        String chargeId = null;

        try {
            Stripe.apiKey = API_SECET_KEY;

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("description","Charge for "+email);
            chargeParams.put("currency","usd");
            chargeParams.put("amount",amount);
            chargeParams.put("source",token);

            Charge charge = Charge.create(chargeParams);


            chargeId = charge.getId();
            Payment payment = new Payment();

            payment.setCustomerId(charge.getId());
            payment.setDate(new Date());
            payment.setAmount(charge.getAmount());
            payment.setDescription("Your Payment is Successful");
            payment.setEmail(email);
            Order order =new Order();
            order.setId(1L);
            order.setPaid(true);
            order.setPaymentType(Payment_type.online);
            payment.setOrder(order);
            paymentRepository.save(payment);
            paymentService.sendWithAttachment(payment,pdfService.toPDF(payment.getId()));

        } catch (Exception e) {
            e.printStackTrace();
        }


        return chargeId;
    }
}

