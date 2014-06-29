package controllers;

import java.util.List;

import net.sf.cglib.core.Local;

import models.Appointment;
import models.ContactInformation;
import models.Location;
import models.PaymentInformation;
import models.PaymentOption;
import models.PaymentStatus;
import models.customer.Customer;
import play.mvc.*;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
public class Profile extends Controller {

	/**
	 * shows user profile page where account settings and personal data can be
	 * viewed and modified
	 * 
	 * @param customerid
	 */
	public static void index(long customerid) {
		Customer customer = Customer.find("byId", customerid).first();
		PaymentInformation paymentInformation = customer.paymentInformation;
		
		if (paymentInformation == null)paymentInformation = new PaymentInformation();

		renderArgs.put("cd1", paymentInformation.getFirstFourDigits());
		renderArgs.put("cd2", paymentInformation.getSecondFourDigits());
		renderArgs.put("cd3",  paymentInformation.getThirdFourDigits());
		renderArgs.put("cd4",  paymentInformation.getForthFourDigits());
		renderArgs.put("customerid", customerid);
		renderArgs.put("customer", customer);

		renderArgs.put("telnumber", customer.contactInformation.telephone);
		renderArgs.put("mobile", customer.contactInformation.mobile);
		renderArgs.put("holderfirstname", paymentInformation.holderFirstName);
		renderArgs.put("holderlastname", paymentInformation.holderLastName);
		renderArgs.put("expiry_month", paymentInformation.cardMonthExp);
		renderArgs.put("expiry_year", paymentInformation.cardYearExp);
		renderArgs.put("cvv", paymentInformation.cvv);
		
		render();
	}

	/**
	 * Shows repair history of user
	 * 
	 * @param customerid
	 */
	public static void history(long customerid) {
		List<Appointment> appointments = Appointment.find("byCustomerId",
				customerid).fetch();
		render(customerid, appointments);
	}

	/**
	 * Post for save personal/account data
	 */
	public static void saveChanges(long customerid, String email,
			String password, String repassword, String firstname,
			String lastname, String street, String streetno, String city,
			String zip, String payopt, String holderfirstname,
			String holderlastname, String cd1, String cd2, String cd3,
			String cd4, String expiry_month, String expiry_year, String cvv, String mobile, String telnumber) {

		Customer customer = Customer.find("byId", customerid).first();
		
		validation.equals("password", password, "repassword", repassword);
		validation.equals("password", password, "old password", customer.password);
		System.out.println("customerid:" + customerid + "card:" + password
				+ street);
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			index(customerid);
		} else {
			
			Location address = Location.find("byId", customer.contactInformation.address.id).first();
			address.street = street;
			address.streetNo = streetno;
			address.city = city;
			address.save();
			
			ContactInformation ci = ContactInformation.find("byId", customer.contactInformation.id).first();
			ci.email = email;
			ci.address = address;
			ci.mobile = mobile;
			ci.telephone = telnumber;
			ci.save();
			
			PaymentInformation paymentInformation = new PaymentInformation();
			paymentInformation.cardMonthExp = expiry_month;
			paymentInformation.cardYearExp = expiry_year;
			paymentInformation.cvv = cvv;
			paymentInformation.holderFirstName = holderfirstname;
			paymentInformation.holderLastName = holderlastname;
			paymentInformation.paymentOption = PaymentOption.valueOf(payopt);
			paymentInformation.cardNumber = cd1 + "" + cd2 + "" + cd3 + ""
					+ cd4;
			paymentInformation.save();
			

			customer.lastName = lastname;
			customer.firstName = firstname;
			customer.contactInformation = ci;	
			customer.paymentInformation = paymentInformation;
			customer.save();

			render(customerid, customer, holderfirstname, holderlastname,
					expiry_month, expiry_year, cvv, cd1, cd2, cd3, cd4);
		}
	}
}
