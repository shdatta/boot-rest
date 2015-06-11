package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Properties;

@Service
public class MailerService {

    @Value("${spring.mail.to}")
    private String mailTo;

    @Autowired
    private JavaMailSender mailSender;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void sendEmail(String subject, String mailBody){
        try {
            Properties mailProperties = new Properties();
            mailProperties.put("mail.smtp.auth", true);
            mailProperties.put("mail.smtp.starttls.enable", true);
            mailProperties.put("mail.from", "blah");
            ((JavaMailSenderImpl)mailSender).setJavaMailProperties(mailProperties);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setSubject(StringUtils.isEmpty(subject) ? "Test" : subject);
            msg.setText(StringUtils.isEmpty(mailBody) ? "Test email" : mailBody);
            msg.setTo(mailTo.split(","));
            mailSender.send(msg);
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

}