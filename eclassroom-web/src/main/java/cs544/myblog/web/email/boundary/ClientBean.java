
package cs544.myblog.web.email.boundary;

import cs544.myblog.web.email.control.MailService;
import cs544.myblog.web.utils.Messages;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author FaRiii
 */
@Named
@RequestScoped
public class ClientBean {
    
    @Autowired
    Messages messageWeb;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    
    public String send() {
        messageWeb.addInfo(null, "Email", "Message Sent !");
        statusMessage = "";
        try {
            MailService.sendMessage(recipient, subject, message); 
        }
        catch(MessagingException ex) {
            statusMessage = ex.getMessage(); 
        }
        return "";  // redisplay page with status message
    }
    
    private String recipient;
    private String subject;
    private String message;
    private String statusMessage = "";
    
}
