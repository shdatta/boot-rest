package hello;


import java.io.Serializable;

public class MailMessage implements Serializable {

    private String subject;
    private String body;

    public MailMessage() {
    }

    public MailMessage(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
