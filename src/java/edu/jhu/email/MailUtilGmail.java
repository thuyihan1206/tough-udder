package edu.jhu.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtilGmail {

   public static void sendMail(
         String to, String from, String subject, String body, 
         boolean bodyIsHTML) throws MessagingException {

      final String username = "cs605782";
      final String password = "hopkins2134";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(username, password);
               }
            });

      try {

         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(
               Message.RecipientType.TO, InternetAddress.parse(to));
         message.setSubject(subject);
         if (bodyIsHTML) {
            message.setContent(body, "text/html");
         }
         else {
            message.setText(body);
         }

         Transport.send(message);

      }
      catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}
