package controllers;

import java.util.List;

import models.Appointment;
import models.PaymentStatus;
import models.customer.Customer;
import play.mvc.*;

public class Profile extends Controller {

	public static void index(long customerid) {
		Customer customer = Customer.find("byId", customerid).first();
		render(customerid,customer);
	}

	public static void history(long customerid) {
		List<Appointment> appointments = Appointment.find("byCustomerId", customerid)
				.fetch();
System.out.print("heyyy"+customerid);
		render(customerid,appointments);
	}
}
