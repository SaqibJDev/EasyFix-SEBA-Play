package controllers;

import models.Appointment;
import models.customer.Customer;
import models.device.DeviceRepair;
import models.rating.Rating;
import models.technician.Technician;
import play.mvc.Controller;

/**
 * Step 1/3 of Payment/Rating UseCase User Feedback - Rate Technician
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 */
public class Feedback extends Controller {
	/**
	 * Shows repair details page and rating form
	 */
	public static void index(long customerId, long repairId) {
		DeviceRepair repair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Appointment appointment = (Appointment) Appointment
				.find("byCustomerIdAndDeviceRepairId", customerId, repairId).first();
		Technician technician = (Technician) Technician
				.find("byTechnicianId", appointment.technicianId).first();

		int size = Rating.find("byId", appointment.ratingId).fetch().size();
		if (size != 0) {
			feedback(customerId, repairId);
		}else {
		render(technician, repair, customerId, appointment);}
	}

	/**
	 * It handles POST request of feedbackForm. User submits feedback
	 * by rating technician using a five value rating bar and posting a comment.
	 * Supports form validation
	 * @param technicianId
	 * @param customerId
	 * @param ratinginput
	 * @param notes
	 */
	public static void submit(long customerId, long repairId,
		int ratinginput, String notes) {
		System.out.println("cid="+customerId+",rid"+repairId+",rating="+ratinginput+",comment="+notes);
		validation.required(ratinginput);
		validation.min(ratinginput, 1);
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
		index(customerId, repairId);// redirects to index
		} else {
			DeviceRepair repair = (DeviceRepair) DeviceRepair
					.find("byId", repairId).fetch(1).get(0);
			System.out.print("app id"+repair.name);

			Appointment appointment = (Appointment) Appointment
					.find("byCustomerIdAndDeviceRepairId", customerId, repairId)
					.first();
			System.out.print("app id"+appointment.id);
			Technician technician = (Technician) Technician
					.find("byId", appointment.technicianId).first();
			Rating rating = new Rating();
			rating.comment = notes;
			rating.rating = ratinginput;
			rating.technician = technician;
			rating.customer = Customer.find("byCustomerId", customerId).first();
			rating.save();
			technician.rating.add(rating);
			String technicianName = technician.firstName + " "
					+ technician.lastName;
			render(technicianName, rating, repair, customerId, appointment);
		}
	}
	
	/**
	 * It displays either user's feedback or feedback form to allow user to submit feedback
	 * @param customerId
	 * @param repairId
	 */
	public static void feedback(long customerId, long repairId){
		System.out.println("feedback: cid="+customerId+",rid"+repairId);
		DeviceRepair repair = (DeviceRepair) DeviceRepair
				.find("byId", repairId).fetch(1).get(0);
		Appointment appointment = (Appointment) Appointment
				.find("byCustomerIdAndDeviceRepairId", customerId, repairId).first();
		Rating rating = (Rating) Rating.find("byId", appointment.ratingId).fetch(1).get(0);
		if (rating != null) {
		Technician technician = (Technician) Technician
				.find("byId", rating.technician.id).first();
		
		render(technician, rating, repair, customerId, appointment);}
		else index(customerId, repairId);
	}
}
