package ConsomiTounsi.API.Stripe1;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ConsomiTounsi.Service.PoolManager;
import ConsomiTounsi.entities.Order;
import ConsomiTounsi.entities.Payment;
import ConsomiTounsi.entities.Payment_type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.Charge;

@Service
public class StripeService1 {

    @Value("${stripe.key.secret}")
    private String API_SECET_KEY;

    @Autowired
    PoolManager pl;

    public StripeService1() {

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
            this.pl.AddamountTopool(charge.getAmount());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return chargeId;
    }

}
