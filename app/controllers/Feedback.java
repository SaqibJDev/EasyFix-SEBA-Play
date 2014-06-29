package controllers;

import models.Appointment;
import models.PaymentStatus;
import models.customer.Customer;
import models.device.DeviceRepair;
import models.rating.Rating;
import models.technician.Technician;
import play.mvc.Controller;

/**
 * Step 1/3 of Payment/Rating UseCase User Feedback - Rate Technician
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 */
public class Feedback extends Controller {
	/**
	 * Shows repair details page and rating form
	 */
	public static void index(long customerid, long repairId) {
		DeviceRepair repair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Appointment appointment = (Appointment) Appointment.find(
				"byCustomerIdAndDeviceRepairId", customerid, repairId).first();
		Technician technician = appointment.getTechnician();
		int size = Rating.find("byId", appointment.ratingId).fetch().size();

		System.out.println("cid=" + customerid + ",rid" + repairId + ",rating="
				+ size + ",tech=" + technician.id);
		if (size != 0) {
			if (appointment.paymentStatus == PaymentStatus.PAID.getIndex())
				paid(customerid, repairId);
			else
				feedback(customerid, repairId);
		} else {
			render(technician, repair, customerid, appointment);
		}
	}

	/**
	 * It handles POST request of feedbackForm. User submits feedback by rating
	 * technician using a five value rating bar and posting a comment. Supports
	 * form validation
	 * 
	 * @param technicianId
	 * @param customerid
	 * @param ratinginput
	 * @param notes
	 */
	public static void submit(long customerid, long repairId, int ratinginput,
			String notes) {
		System.out.println("cid=" + customerid + ",rid" + repairId + ",rating="
				+ ratinginput + ",comment=" + notes);
		validation.required(ratinginput);
		validation.min(ratinginput, 1);
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			index(customerid, repairId);// redirects to index
		} else {
			DeviceRepair repair = (DeviceRepair) DeviceRepair
					.find("byId", repairId).fetch(1).get(0);

			Appointment appointment = (Appointment) Appointment.find(
					"byCustomerIdAndDeviceRepairId", customerid, repairId)
					.first();
			Technician technician = appointment.getTechnician();
			Rating rating = new Rating(0);
			rating.comment = notes;
			rating.rating = ratinginput;
			rating.technician = technician;
			rating.customer = Customer.find("byId", customerid).first();
			rating.save();
			technician.rating.add(rating);
			technician.save();
			appointment.ratingId = rating.id;
			appointment.save();
			String technicianName = technician.firstName + " "
					+ technician.lastName;
			render(technicianName, rating, repair, customerid, appointment);
		}
	}
	/**
	 * It displays either user's feedback or feedback form to allow user to
	 * submit feedback
	 * 
	 * @param customerid
	 * @param repairId
	 */
	public static void paid(long customerid, long repairId) {
		System.out.println("feedback: cid=" + customerid + ",rid" + repairId);
		DeviceRepair repair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Appointment appointment = (Appointment) Appointment.find(
				"byCustomerIdAndDeviceRepairId", customerid, repairId).first();
		Rating rating = appointment.getRating();
		if (rating != null) {
			Technician technician = appointment.getTechnician();
			render(technician, rating, repair, customerid, appointment);
		} else
			index(customerid, repairId);
	}
	/**
	 * It displays either user's feedback or feedback form to allow user to
	 * submit feedback
	 * 
	 * @param customerid
	 * @param repairId
	 */
	public static void feedback(long customerid, long repairId) {
		System.out.println("feedback: cid=" + customerid + ",rid" + repairId);
		DeviceRepair repair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Appointment appointment = (Appointment) Appointment.find(
				"byCustomerIdAndDeviceRepairId", customerid, repairId).first();
		Rating rating = appointment.getRating();
		if (rating != null) {
			Technician technician = appointment.getTechnician();
			render(technician, rating, repair, customerid, appointment);
		} else
			index(customerid, repairId);
	}
}
