package notifiers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.Actor;

import org.apache.commons.mail.EmailAttachment;
 
public class Mails extends play.mvc.Mailer {
 public static final String fromEmail = "EasyFix <no-reply@easyfix.com>";
   public static void welcome(Actor user) {
      setSubject("Welcome %s", user.firstName);
      addRecipient(user.email);
      setFrom(fromEmail);
//      EmailAttachment attachment = new EmailAttachment();
//      attachment.setDescription("A pdf document");
//      attachment.setPath(Play.getFile("rules.pdf").getPath());
//      addAttachment(attachment);
      send(user);
   }
 
   public static boolean lostPassword(Actor user) {
      String newpassword = user.password;
      setFrom(fromEmail);
      setSubject("Your password has been reset");
      addRecipient(user.email);
      send(user, newpassword);
      return true;
   }
 
}
