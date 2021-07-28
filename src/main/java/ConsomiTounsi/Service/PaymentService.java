package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Payment;
import ConsomiTounsi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.io.File;

@Service
public class PaymentService {
    //ebde lehni
    private JavaMailSender javaMailSender;
    @Autowired
    public PaymentService(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }
    //habes lehni
    @Autowired
    PaymentRepository pr;

    //ebde lehni
    public void sendEmailStripe( String email , String text) throws MailException, MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("consommitounsi2@gmail.com");
        helper.setTo(email);
        helper.setSubject("stripe");
        helper.setText(text);}


    public void sendEmail(String sendTo,String text) throws MailException, MessagingException {
        /*MimeMessage message = this.javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("consommitounsi2@gmail.com");
        helper.setTo(sendTo);
        helper.setSubject("Payment");
        helper.setText(text);
        String fileName = "config/test.pdf";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());
        helper.addAttachment("Payment details", file);

        this.javaMailSender.send(message);

        /*

         */
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setFrom("consommitounsi2@gmail.com");
        simpleMailMessage.setSubject("Payment");
        simpleMailMessage.setText(text);
        this.javaMailSender.send(simpleMailMessage);


    }
//find All Payment
//habes lehni
    public List<Payment> retrieveAllPayments() {
        return (List<Payment>) pr.findAll();
    }

//Add Payment

    public Payment AddPayment(Payment payment) throws MessagingException {
        //hethi
        this.sendEmail("mouad19971@gmail.com","Payment done");
        return pr.save(payment);
    }

//delete Payment

    public void deletePayment(Long id) {
        Optional<Payment> optionalOrder=pr.findById(id);
        if (!optionalOrder.isPresent()){
            throw new IllegalStateException("Payment Not Found");
        }
        pr.deleteById(id);
    }
    // Update Payment
    public Payment updatePayment(Long id ,Payment P) {
        Optional<Payment> optionalPayment=pr.findById(id);
        if (!optionalPayment.isPresent()){
            throw new IllegalStateException("Id Not Found");
        }
        Payment paymentLegacy = optionalPayment.get();
        if (P.getDate()!= null||P.getDate()!=(paymentLegacy.getDate())){
            paymentLegacy.setDate(P.getDate());}
        if (P.getDescription()!=null ||!P.getDescription().equals(paymentLegacy.getDescription())){
            paymentLegacy.setDate(P.getDate());}

        if (P.getOrder()!=null){
            paymentLegacy.setOrder(P.getOrder());
        }
        return (paymentLegacy);
}
//find By Id
    public Optional<Payment> FindPayment(Long id) {

        return   pr.findById(id);
    }

    public void sendWithAttachment(Payment payment,String attachement) throws MailException, MessagingException {


        //
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        StringBuilder buf = new StringBuilder();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //
        helper.setTo(payment.getEmail());
        helper.setSubject("thank you very much for your shopping with us /");
        //helper.setText(messaage);
        helper.setText("hello "+payment.getEmail()+" here is your Payment information");

        //

        File file = new File("C:\\Users\\mouad\\payment\\"+ attachement);
        helper.addAttachment(file.getName(), file);

        javaMailSender.send(mimeMessage);
    }


}
