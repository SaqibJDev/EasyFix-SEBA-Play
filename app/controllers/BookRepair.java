package controllers;

import groovy.ui.text.FindReplaceUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import notifiers.Mails;

import org.joda.time.DateTime;

import controllers.Secure.Security;
import models.Actor;
import models.Appointment;
import models.GeoPoint;
import models.Location;
import models.PaymentStatus;
import models.customer.Customer;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.device.Manufacturer;
import models.technician.Technician;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com / Changed By Hafiz Saqib Javed
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
    /**
     * Step1/3 check technicians availability. to get the internal technicians
     * who can repair the damage and go to the given location
     * <p>
     * Response: list of technicinas who can go to the location otherwise
     * (list==null) no tech available message 
     * 
     * @param model
     * @param repair
     * @param location
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
    
    
    /**
     * Step2/4 appointment and customer details we need to check validity of
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
    public static void personalInformationForBookRepair(String maker, String deviceModel, String repair, long repair_id, long technician, String date, String time, String location, float longitude, float latitude) {
    	
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
    	renderArgs.put("technician_id", technician);
    	renderArgs.put("date", date);
    	renderArgs.put("time", time);
    	renderArgs.put("location", location);
    	renderArgs.put("latitude", latitude);
    	renderArgs.put("longitude", longitude);
        
        render();
    }
    
    /**
     * Step3/4 review appointment
     * 
     * @param repairId
     * @param technicianId
     * @param date
     * @param time
     * @param notes
     * @param location
     */
    public static void reviewAppointment(String maker, String deviceModel, String repair, String notes, long technician_id, long repair_id, String date, String time, String location, float longitude, float latitude) {
    	// Fetch Device Repair details
    	renderArgs.put("maker", maker);
    	renderArgs.put("model", deviceModel);
    	renderArgs.put("repair", repair);
    	
    	DeviceRepair deviceRepair = DeviceRepair.findById(repair_id);
    	if(deviceRepair!=null){
    		renderArgs.put("repairTime", deviceRepair.repairTime);
    		renderArgs.put("repairCost", deviceRepair.price);
    	}
    	
    	Technician technician = Technician.findById(technician_id);
    	if(technician!=null){
    		renderArgs.put("technicianName", technician.firstName+" "+technician.lastName);
    		renderArgs.put("technicianContact", technician.contactInformation.mobile);
    	}
    	
    	renderArgs.put("date", date);
    	renderArgs.put("time", time);
    	renderArgs.put("notes", notes);
    	renderArgs.put("repair_id", repair_id);
    	renderArgs.put("technician_id", technician_id);
    	renderArgs.put("location", location);
    	renderArgs.put("latitude", latitude);
    	renderArgs.put("longitude", longitude);
		
        render(deviceModel,repair, maker);
}

    /**
     * Step4/4 appointment confirmation
     * 
     * @param repairId
     * @param technicianId
     * @param date
     * @param time
     * @param notes
     */
    public static void appointmentConfirmation(long repair_id, long technician_id, String date, String time, String notes, String location, float longitude, float latitude) {
    	try{
    		String []timeArr = time.split(" ");
    		if(timeArr[1].equals("PM")){
        		timeArr[0] = (Integer.parseInt(timeArr[0].split(":")[0])+12)+":"+timeArr[0].split(":")[1];
        	}
        	Appointment appointment = new Appointment();
        	Customer customer = Customer.find("byEmail", Security.connected()).first();
        	appointment.customerId = customer.id;
        	appointment.technicianId = technician_id;
        	appointment.deviceRepairId = repair_id;
        	DeviceRepair deviceRepair = DeviceRepair.findById(repair_id);
        	appointment.duration = deviceRepair.repairTime;
        	appointment.paymentStatus = PaymentStatus.PENDING.getIndex();
        	Location locationObj = new Location(null, location, null, null, null);
        	locationObj.geoPoint = new GeoPoint(latitude, longitude);
        	locationObj.save();
        	appointment.meetingPlace = locationObj;
        	java.sql.Timestamp date2;
    		try {
    			Date tempDate = (Date) new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date+" "+timeArr[0]);
    			date2 = new java.sql.Timestamp(tempDate.getTime());
    			appointment.dateTimeStart = date2;
    			System.out.println(date2.toString());
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
        	
        	appointment.save();
        	Mails.bookRepairConfirmation(appointment, customer, notes);
        	render();
    	}catch(Exception e){
    		
    	}
    	
    }
}
