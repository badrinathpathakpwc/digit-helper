import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
   public static void main(String[] args) {
      // Change the Recipient's email address
      String to = "badrinathpathak.pwc@gmail.com";

      // Change the Sender's email address
      String from = "sujog@odishaone.gov.in";
      
      //change login name accordingly
      final String username = "sujog@odishaone.gov.in";
      
      //change login password accordingly
      final String password = "D$12#s!2@2@g";
	
      // Use SMTP server address. 
      String host = "apps.odishaone.gov.in";

      Properties props = new Properties();
      
      //Enable SMTP Authentication Mode
      props.put("mail.smtp.auth", "true");

      //Enable STARTTLS Authentication Mode
      props.put("mail.smtp.starttls.enable", "true");
     
      //Use String host
      props.put("mail.smtp.host", host);

      //Use SMTP Port
      props.put("mail.smtp.port", "25");

      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {

         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Test Subject");

         // This mail will have two parts, BODY and embedded image
         MimeMultipart multipart = new MimeMultipart("related");

         // Add body part (the html)
         BodyPart messageBodyPart = new MimeBodyPart();
         String htmlText = "<H1>Test Body</H1>";
         messageBodyPart.setContent(htmlText, "text/html");
         
	// Add it
         multipart.addBodyPart(messageBodyPart);


         // Put everything together
         message.setContent(multipart);
         
	// Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (Exception e) {
        System.out.println(e);
         throw new RuntimeException(e);
      }
   }
}
