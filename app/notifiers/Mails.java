package notifiers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.Actor;

import org.apache.commons.mail.EmailAttachment;
 
public class Mails extends play.mvc.Mailer {
 
   public static void welcome(Actor user) {
      setSubject("Welcome %s", user.firstName);
      addRecipient(user.email);
      setFrom("Me <me@me.com>");
      EmailAttachment attachment = new EmailAttachment();
      attachment.setDescription("A pdf document");
      attachment.setPath(Play.getFile("rules.pdf").getPath());
      addAttachment(attachment);
      send(user);
   }
 
   public static boolean lostPassword(Actor user) {
      String newpassword = user.password;
      setFrom("Robot <robot@thecompany.com>");
      setSubject("Your password has been reset");
      addRecipient(user.email);
      send(user, newpassword);
      return true;
   }
 
}
