package ConsomiTounsi.configuration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to , String body , String subject ) {
        try {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
                message.setFrom("ITCEQ.GestionConge@gmail.com");
                message.setTo(to);
                message.setText(body, true);
                message.setSubject(subject);
        mailSender.send(mimeMessage);
    } catch (MessagingException e) {
        throw new IllegalStateException("failed to send email");
    }
}
}

