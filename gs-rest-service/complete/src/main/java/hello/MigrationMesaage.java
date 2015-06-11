package hello;

import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.Serializable;


public class MigrationMesaage<T extends Serializable> implements MessageCreator {

    private Serializable t;

    public Serializable getT() {
        return t;
    }

    public void setT(Serializable t) {
        this.t = t;
    }

    public MigrationMesaage(String subject, String body) {

        this.t = new MailMessage(subject, body);
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        ObjectMessage objectMessage = session.createObjectMessage();
        objectMessage.setObject(t);
        return objectMessage;
    }
}
