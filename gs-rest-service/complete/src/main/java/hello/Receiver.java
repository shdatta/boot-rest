package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    ConfigurableApplicationContext context;

    @Autowired
    private MailerService mailerService;

    @JmsListener(destination = "mailbox-destination")//, containerFactory = "myJmsContainerFactory")
    public void receiveMessage(MailMessage message) {
        mailerService.sendEmail(message.getSubject(), message.getBody());
    }
}
