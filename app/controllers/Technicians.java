package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controllers.Secure.Security;
import models.Actor;
import models.ajaxResponse.TechnicianMap;
import models.device.DeviceRepair;
import models.technician.Technician;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
public class Technicians extends Application {

	/*
	 * Show external technicians who offer repair services for selected device model and repair
	 */
    public static void show(String maker, String deviceModel, String repair) {
        List<Technician> exTechnicians = Technician.findTechniciansByRepair(
                Technician.findTechniciansByIsExternal(true), deviceModel,
                repair);
        DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
                .find("byName", repair).fetch(1).get(0);
        if (exTechnicians != null) {
            Collections.sort(exTechnicians, new Comparator<Technician>() {
                public int compare(Technician o1, Technician o2) {
                    return o1.lastName.compareTo(o2.lastName);
                }
            });
            render(exTechnicians, deviceRepair, deviceModel, repair, maker);
        } else
            render();
    }
    
	/*
	 * Show list of all external technicians
	 */
    public static void showAll() {
        List<Technician> exTechnicians = Technician.findTechniciansByIsExternal(true);
        
        if (exTechnicians != null && exTechnicians.size() > 0) {
            Collections.sort(exTechnicians, new Comparator<Technician>() {
                public int compare(Technician o1, Technician o2) {
                    return o1.lastName.compareTo(o2.lastName);
                }
            });
            render(exTechnicians);
        } else
            render();
    }
    
    /*
     * Show details of external technician company/show, address, Contact link
     */
    public static void details(String maker, String deviceModel, String repair, String name) {
        List<Technician> exTechnicians = Technician.findTechniciansByIsExternal(true);
        
        if (exTechnicians != null && exTechnicians.size() > 0) {
            for (Technician technician : exTechnicians) {
				if(technician.title.equals(name)){
					render(technician, maker, deviceModel, repair, name);
					break;
				}
			}
            
        } else
            render();
    }
    
    
    /*
     * Get technicians list for map
     */
    public static String getTechnicians(){
    	List<Technician> technicians = Technician.findTechniciansByIsExternal(false);

    	List<TechnicianMap> techniciansMapList = new ArrayList<TechnicianMap>();
    	
    	for (Technician technician : technicians) {
//    		new TechnicianMap(technician.firstName + " "+ technician.lastName, technician.id, technician.contactInformation.address.geoPoint.latitude, technician.contactInformation.address.geoPoint.longitude, (float)4.0)
    		TechnicianMap tm = new TechnicianMap();
    		tm.id = technician.id;
    		tm.name = technician.firstName + " "+ technician.lastName;
    		tm.longitude = technician.contactInformation.address.geoPoint.longitude;
    		tm.latitude = technician.contactInformation.address.geoPoint.latitude;
    		tm.rating = (float) 4.0;
    		techniciansMapList.add(tm);
		}
    	Gson gsonHandler = new Gson();
    	String returnResult = gsonHandler.toJson(technicians);
    	System.out.println( returnResult);    	
//    	return "{\"data\":"+gsonHandler.toJson(techniciansMapList)+"}";
    	return gsonHandler.toJson(techniciansMapList);
    }
    
    /*
     * Get technicians list for map
     */
    public static String getCloseTechnicians(float userLatitude, float userLongitude, float maxDistance){
    	List<Technician> technicians = Technician.findTechniciansByIsExternal(false);

    	List<TechnicianMap> techniciansMapList = new ArrayList<TechnicianMap>();
    	
    	double deltaLat, deltaLng, distance;
    	
    	for (Technician technician : technicians) {
  		
    		deltaLat = technician.contactInformation.address.geoPoint.latitude - userLatitude;
    		deltaLng = technician.contactInformation.address.geoPoint.longitude - userLongitude;
    		
    		distance = Math.pow(Math.sin(deltaLat/2), 2) + Math.cos(technician.contactInformation.address.geoPoint.latitude) * Math.cos(userLatitude) * Math.pow(Math.sin(deltaLng/2),2);
    		distance = 2 * Math.atan2( Math.sqrt(distance), Math.sqrt(1-distance) ) * 6373000; //last number is radius of earth, converts into [m] distance
    		
    		if(distance>maxDistance)
    			continue;

    		TechnicianMap tm = new TechnicianMap();
    		tm.id = technician.id;
    		tm.name = technician.firstName + " "+ technician.lastName;
    		tm.longitude = technician.contactInformation.address.geoPoint.longitude;
    		tm.latitude = technician.contactInformation.address.geoPoint.latitude;
    		tm.rating = (float) 4.0;
    		//tm.distance = distance;
    		techniciansMapList.add(tm);
		}
    	Gson gsonHandler = new Gson();
    	String returnResult = gsonHandler.toJson(technicians);
    	System.out.println( returnResult);    	
//    	return "{\"data\":"+gsonHandler.toJson(techniciansMapList)+"}";
    	return gsonHandler.toJson(techniciansMapList);
    }
    
   

}