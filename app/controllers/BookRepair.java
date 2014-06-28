package controllers;

import groovy.ui.text.FindReplaceUtility;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import controllers.Secure.Security;
import models.Actor;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.device.Manufacturer;
import models.technician.Technician;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@With(Secure.class)
public class BookRepair extends Application {

    /********************* BOOK REPAIR CODE START **********************************/

    // Internal Technicians
	/*
	 * Show map where user can select its location
	 * Show list of available technicians according to User selected location
	 * User selects Date and time
	 */
    public static void index(String maker, String deviceModel, String repair) {
    	Manufacturer manufacturer = (Manufacturer) Manufacturer.find("byName", maker).fetch().get(0);
    	for (DeviceModel model : manufacturer.deviceModels) {
			if(model.name.equals(deviceModel))
			{
				
				for (DeviceRepair deviceRepair : model.deviceRepairList) {
					if(deviceRepair.name.equals(repair)){
						renderArgs.put("repairTime", deviceRepair.repairTime);
						renderArgs.put("repairCost", deviceRepair.price);
						renderArgs.put("repair_id", deviceRepair.id);
						break;
					}
				}
				break;
			}
		} 
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
    public static void personalInformationForBookRepair(String maker, String deviceModel, String repair, long repair_id) {
    	
    	// Fetch User profile attributes
    	Actor user = Actor.find("byEmail", Security.connected()).first();
        renderArgs.put("firstName", user.firstName);
        renderArgs.put("lastName", user.lastName);
        renderArgs.put("street", user.contactInformation.address.street);
        renderArgs.put("streetNo", user.contactInformation.address.streetNo);
        renderArgs.put("zip", user.contactInformation.address.zip);
        renderArgs.put("city", user.contactInformation.address.city);
        renderArgs.put("phone", user.contactInformation.telephone);
        renderArgs.put("mobile", user.contactInformation.mobile);
        
        renderArgs.put("maker", maker);
    	renderArgs.put("deviceModel", deviceModel);
    	renderArgs.put("repair", repair);
    	renderArgs.put("repair_id", repair_id);
        
        render();
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
    
    public static void reviewAppointment(String maker, String deviceModel, String repair, String notes, long technician_id, long repair_id) {
    	System.out.println(notes);
    	// Fetch Device Repair details
        renderArgs.put("maker", maker);
    	renderArgs.put("model", deviceModel);
    	renderArgs.put("repair", repair);
    	
    	DeviceRepair deviceRepair = DeviceRepair.findById(repair_id);
    	if(deviceRepair==null){
    		System.out.println("it is Null");
    	}else{
    		System.out.println("not null" +deviceRepair.repairTime);
    		renderArgs.put("repairTime", deviceRepair.repairTime);
    		renderArgs.put("repairCost", deviceRepair.price);
    	}
    	
		
//    	Manufacturer manufacturer = (Manufacturer) Manufacturer.find("byName", maker).fetch().get(0);
//    	for (DeviceModel model : manufacturer.deviceModels) {
//			if(model.name.equals(deviceModel))
//			{
//				
//				for (DeviceRepair deviceRepair : model.deviceRepairList) {
//					if(deviceRepair.name.equals(repair)){
//						renderArgs.put("repairTime", deviceRepair.repairTime);
//						renderArgs.put("repairCost", deviceRepair.price);
//						break;
//					}
//				}
//				break;
//			}
//		} 
    	
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
