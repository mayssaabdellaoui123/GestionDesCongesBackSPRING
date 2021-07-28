package ConsomiTounsi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailSenderService1 {
        @Autowired
        private JavaMailSender mailSender;

        public EmailSenderService1(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        public void sendEmail(String to , String body , String subject ) {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
                message.setFrom("tn.consomi.tounsi@gmail.com");
                message.setTo(to);
                message.setText(body, true);
                message.setSubject(subject);
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new IllegalStateException("failed to send email");
            }
        }


}
