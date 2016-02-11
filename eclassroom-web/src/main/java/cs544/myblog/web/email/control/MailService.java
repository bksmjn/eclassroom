
package cs544.myblog.web.email.control;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author FaRiii
 */
public class MailService {

    /**
     * Sends a subject and message to a recipient
     *
     * @param recipient Internet address of the recipient
     * @param subject the subject of the message
     * @param message the message
     * @throws MessagingException
     */
    public static void sendMessage(String recipient, String subject, String message) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.from", FROM);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASSWORD);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(FROM));
        mimeMessage.setSender(new InternetAddress(FROM));
        mimeMessage.setSubject(subject);
        mimeMessage.setSentDate(new Date());
        mimeMessage.setContent(message, "text/plain");

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        Transport.send(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));

    }

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final String USER = "eclass.maharishi@gmail.com";     // Must be valid user in d.umn.edu domain, e.g. "smit0012"
    private static final String PASSWORD = "12345@678"; // Must be valid password for smit0012
    private static final String FROM = "eclass.maharishi@gmail.com";     // Full info for user, e.g. "Fred Smith <smit0012@d.umn.edu>"
}
