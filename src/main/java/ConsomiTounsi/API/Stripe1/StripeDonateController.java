package ConsomiTounsi.API.Stripe1;

import ConsomiTounsi.Service.PoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StripeDonateController {
    @Value("${stripe.key.public}")
    private String API_PUBLIC_KEY;
    @Autowired
    PoolManager pl;

    private StripeService1 stripeService1;

    public StripeDonateController(StripeService1 stripeService1) {
        this.stripeService1 = stripeService1;
    }

    @GetMapping("/1")
    public String homepage() {
        return "homepage1";
    }

    @GetMapping("/charge1")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "donate";
    }

    @PostMapping("/create-charge1")
    public @ResponseBody Response createCharge(String email, String token) {

        if (token == null) {
            return new Response(false, "Stripe donate token is missing. please try again later.");
        }

        String chargeId = stripeService1.createCharge(email, token, 111);// 9.99 usd
        System.out.print(chargeId);
        this.pl.AddamountTopool(111);

        if (chargeId == null) {
            return new Response(false, "An error accurred while trying to charge.");
        }

        // You may want to store charge id along with order information

        return new Response(true, "Success your charge id is " + chargeId);
    }
}
