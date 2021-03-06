package controllers;

import notifiers.Mails;
import models.Actor;
import models.Appointment;
import models.PaymentInformation;
import models.PaymentStatus;
import models.customer.Customer;
import models.device.DeviceRepair;
import models.technician.Technician;
import play.mvc.*;

/**
 * Step 2/3 (payment data submission) and 3/3(confirmation) of Payment
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
public class Payment extends Application {

	/**
	 * index page where user can choose option to pay for repair
	 */
	public static void index(long repairId, long customerid, long appointmentId) {
		DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Customer customer = (Customer) Customer.find("byId", customerid)
				.fetch(1).get(0);
		PaymentInformation paymentInformation = customer.paymentInformation;
		if (paymentInformation == null)
			paymentInformation = new PaymentInformation();
		render(deviceRepair, customer, paymentInformation, appointmentId);
	}

	/**
	 * POST action for Paymentform
	 */
	public static void paymentDetails(String firstname, String lastname,
			String cd1, String cd2, String cd3, String cd4,
			String expiry_month, String expiry_year, String password,
			long customerid, long repairId, long appointmentId) {
		validation.required(lastname);
		validation.required(firstname);
		validation.required(cd1);
		validation.required(cd2);
		validation.required(cd3);
		validation.required(cd4);
		validation.required(password);

		DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).first();
		Customer customer = (Customer) Customer.find("byId", customerid).first();

		if (validation.hasErrors()) {
			for (play.data.validation.Error error : validation.errors()) {
				System.out.println(error.message());
			}
			render(deviceRepair, customer, appointmentId);
		} else {

			System.out.println("lastname = " +deviceRepair.description);
			String cardnumber = cd1 + "" + cd2 + cd3 + cd4 + "";
			String holder = firstname + " " + lastname;
			float amount = deviceRepair.price;
			review(holder, cardnumber, amount, customerid, repairId,appointmentId);
			
		}
	}

	public static void review(String holder, String cardnumber, float amount, long customerid, long repairId, long appointmentId) {
		 DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
		  .find("byId", repairId).first();
		 
		render(holder, cardnumber, amount, customerid, repairId, deviceRepair,appointmentId);
	}

	/**
	 * Submits Payment, sends user email and redirects user to confirmation page
	 */
	public static void paymentConfirmation(long customerid, long repairId, long appointmentId) {
		try{
			DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair.find("byId", repairId).fetch(1).get(0);
			Customer customer = (Customer) Customer.find("byId", customerid)
					.fetch(1).get(0);

			Appointment appointment = (Appointment) Appointment.findById(appointmentId);
			appointment.paymentStatus = PaymentStatus.PAID.getIndex();
			appointment.save();
			Mails.paymentConfirmation(deviceRepair, customer);
			render();
		}catch(Exception e){
			
		}
				
	}

}
