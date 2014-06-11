package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.device.DeviceModel;
import models.technician.Technician;
import play.data.validation.Required;
import play.mvc.Controller;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
public class BookRepair extends Controller {

    /********************* BOOK REPAIR CODE START **********************************/

    // Internal Technicians
	/*
	 * Show map where user can select its location
	 * Show list of available technicians according to User selected location
	 * User selects Date and time
	 */
    public static void index(String maker, String deviceModel, String repair) {
        
            render(deviceModel,repair, maker);
    }
    // TODO we need static page for mock-up with map
    public static void checkTechniciansAvailability(String deviceModel, String repair) {
        List<Technician> inTechnicians = Technician.findTechniciansByRepair(
                Technician.findTechniciansByIsExternal(false), deviceModel,
                repair);
        if (inTechnicians != null) {
            Collections.sort(inTechnicians, new Comparator<Technician>() {
                public int compare(Technician o1, Technician o2) {
                    return o1.lastName.compareTo(o2.lastName);
                }
            });
            render(inTechnicians);
        } else
            render();
    }

    /**
     * Step1/3 check technicians availability. to get the internal technicians
     * who can repair the damage and go to the given location
     * <p>
     * Response: list of technicinas who can go to the location otherwise
     * (list==null) no tech available message //TODO
     * 
     * @param model
     * @param repair
     * @param location
     */
    public static void modelRepairTechnicianByLocation(String model,
            String repair, @Required String location) {
        List<Technician> techs = Technician.findByAddress(location);
        DeviceModel dm = DeviceModel.find("byName", model).first();
        techs = Technician.findTechniciansByRepair(techs, dm.name, repair);
        if (techs != null) {
            Collections.sort(techs, new Comparator<Technician>() {

                public int compare(Technician o1, Technician o2) {
                    return o1.lastName.compareTo(o2.lastName);
                }
            });
            render(techs);
        } else
            render("No technician for " + repair
                    + " repair available for this location");
    }

    /**
     * to check technicians availability for the given timeslot . if he is
     * available create appointment and set datetime infomration. To do so first
     * check if it is within workinghourinterval and day, if it is then compare
     * given period datetime + repairduration to free timeslots of technician
     * and fix appointment. Response: if technician is available user redirect
     * to step2 otherwise user gets error message
     * 
     * @param date
     * @param time
     */
//    public static void modelRepairTechnicianAvailability(String date,
//            String time) {
//        // TODO currently not working
//    }
//    
    public static void personalInformationForBookRepair(String maker, String deviceModel, String repair) {
    	
    	render(maker, deviceModel, repair);
    }

    /**
     * Step2/3 appointment and customer details we need to check validity of
     * fields using javascript on client. response: if user filled in the form
     * correctly, he is redirected to step3 otherwise gets error messages
     * 
     * @param firstname
     * @param lastname
     * @param street
     * @param city
     * @param plz
     * @param tel
     * @param notes
     */
    public static void modelRepairTechnicianAppointment(String firstname,
            String lastname, String street, String city, String plz,
            String tel, String notes) {
        // TODO currently not working
    }
    
    public static void reviewAppointment(String maker, String deviceModel, String repair) {
        
        render(deviceModel,repair, maker);
}

    /**
     * Step3/3 appointment confirmation
     * 
     * @param appointmentId
     */
    public static void appointmentConfirmation(String appointmentId) {
        // TODO currently not working
    }
}
