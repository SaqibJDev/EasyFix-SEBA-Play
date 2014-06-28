package controllers;

import notifiers.Mails;
import models.Actor;
import models.Appointment;
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
public class Payment extends Controller {

	/**
	 * index page where user can choose option to pay for repair
	 */
	public static void index(long repairId, long customerId) {
		System.out.println("repairId = " + repairId + ", customerId = "
				+ customerId);
		DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Customer customer = (Customer) Customer.find("byId", customerId)
				.fetch(1).get(0);
		render(deviceRepair, customer);
	}

	/**
	 * POST action for Paymentform
	 */
	public static void paymentDetails(String firstname, String lastname,
			String cd1, String cd2, String cd3, String cd4,
			String expiry_month, String expiry_year, String password,
			long customerId, long repairId) {
		System.out.println("lastname = " + lastname + ", firstName = "
				+ firstname + ", cardNumber = " + cd1+"cid="+customerId+"rid="+repairId);
		validation.required(lastname);
		validation.required(firstname);
		validation.required(cd1);
		validation.required(cd2);
		validation.required(cd3);
		validation.required(cd4);
		validation.required(password);

		DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).first();
		Customer customer = (Customer) Customer.find("byId", customerId).first();

		System.out.println("lastname = " +deviceRepair.description);
		if (validation.hasErrors()) {
			for (play.data.validation.Error error : validation.errors()) {
				System.out.println(error.message());
			}
			render(deviceRepair, customer);
		} else {

			System.out.println("lastname = " +deviceRepair.description);
			String cardnumber = cd1 + "" + cd2 + cd3 + cd4 + "";
			String holder = firstname + " " + lastname;
			float amount = deviceRepair.price;
			review(holder, cardnumber, amount, customerId, repairId);
			
		}
	}

	public static void review(String holder, String cardnumber, float amount, long customerId, long repairId) {
		 DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
		  .find("byId", repairId).first();
		 
		render(holder, cardnumber, amount, customerId, repairId, deviceRepair);
	}

	/**
	 * Submits Payment, sends user email and redirects user to confirmation page
	 */
	public static void paymentConfirmation(long customerId, long repairId) {
		DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Customer customer = (Customer) Customer.find("byId", customerId)
				.fetch(1).get(0);

		Appointment appointment = (Appointment) Appointment
				.find("byCustomerIdAndDeviceRepairId", customerId, repairId).first();
		appointment.paymentStatus = PaymentStatus.PAID.getIndex();
		Mails.paymentConfirmation(deviceRepair, customer);
		render();
	}

}
