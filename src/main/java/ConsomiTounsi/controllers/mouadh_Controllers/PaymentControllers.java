package ConsomiTounsi.controllers.mouadh_Controllers;

import ConsomiTounsi.Service.PaymentService;
import ConsomiTounsi.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
@RestController
@RequestMapping("/payment")

public class PaymentControllers {
    @Autowired
    PaymentService om;
    //Create A new Payment
    @PostMapping
    ResponseEntity<?> createNewPayment(@RequestBody Payment o){
        try
        {
            om.AddPayment(o);
            return new ResponseEntity<>(new MessageResponseModel("Payment Added "), HttpStatus.CREATED);
        }catch(MessagingException ex)
        {
            return new ResponseEntity<>("Sending email failed", HttpStatus.CREATED);
        }

         }
    //Show All Payments
    @GetMapping
    ResponseEntity<?> getAllPayment(){
        List<Payment> o=om.retrieveAllPayments();
        GetAllPaymentResponse Payments = new GetAllPaymentResponse(o);
        return new ResponseEntity<>(Payments,HttpStatus.OK);
    }
    //Show a Payment by id
    @GetMapping("/{id}")
    ResponseEntity<?> getPaymentById(@PathVariable Long id){
        return new ResponseEntity<>(om.FindPayment(id),HttpStatus.OK);

    }
    //Delete a Payment by id
    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePaymentById(@PathVariable Long id){
        om.deletePayment(id);
        return new ResponseEntity<>(new MessageResponseModel("Payment Deleted"),HttpStatus.OK);

    }
    //Update Payment
    @PutMapping("/{id}")
    ResponseEntity<?> updatePayment(@PathVariable Long id ,@RequestBody Payment o){

        om.updatePayment(id,o);
        return new ResponseEntity<>(new MessageResponseModel("Payment Updated"), HttpStatus.OK);
    }
    //find A Payment By Type


}
