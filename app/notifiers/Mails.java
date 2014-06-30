package notifiers;

import models.Actor;
import models.Appointment;
import models.customer.Customer;
import models.device.DeviceRepair;
 
/*
 * Mail notification API
 */
public class Mails extends play.mvc.Mailer {
 public static final String fromEmail = "EasyFix <no-reply@easyfix.com>";
 
 /*
  * Welcome mail sent on signup
  */
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

   /*
    * Payment confirmation for an appointment
    */
   public static void paymentConfirmation(DeviceRepair repair, Customer customer) {
	      setSubject("Payment Confirmation %s", customer.firstName);
	      addRecipient(customer.email);
	      setFrom("Me <me@me.com>");
	      send(repair, customer);
	   }
   
   /*
    * Book Appointment confirmation
    */
   public static void bookRepairConfirmation(Appointment appointment, Customer customer, String notes) {
	      setSubject("Book Repair Confirmation %s", customer.firstName);
	      addRecipient(customer.email);
	      setFrom("Me <me@me.com>");
	      send(appointment, customer, notes);
	   }
}
