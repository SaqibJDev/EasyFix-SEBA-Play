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
import models.rating.Rating;
import models.technician.Technician;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
public class Technicians extends Application {

   
    /*
     * Get technicians list for map
     */
    public static String getTechnicians(){
    	List<Technician> technicians = Technician.findTechniciansByIsExternal(false);

    	List<TechnicianMap> techniciansMapList = new ArrayList<TechnicianMap>();
    	
    	for (Technician technician : technicians) {
    		TechnicianMap tm = new TechnicianMap();
    		tm.id = technician.id;
    		tm.name = technician.firstName + " "+ technician.lastName;
    		tm.longtitude = technician.contactInformation.address.geoPoint.longtitude;
    		tm.latitude = technician.contactInformation.address.geoPoint.latitude;
    		tm.rating = (float) 4.0;
    		techniciansMapList.add(tm);
		}
    	Gson gsonHandler = new Gson();
    	String returnResult = gsonHandler.toJson(technicians);
    	System.out.println( returnResult);    	
    	return gsonHandler.toJson(techniciansMapList);
    }
    
    
    /*
     * Get technicians list for map
     */
    public static String getCloseTechnicians(float userLatitude, float userLongitude, float maxDistance, boolean closeTechnicians){
    	List<Technician> technicians = Technician.findTechniciansByIsExternal(false);
//maxDistance = 10000;
    	List<TechnicianMap> techniciansMapList = new ArrayList<TechnicianMap>();
    	
    	double deltaLat, deltaLng, distance;
    	
    	for (Technician technician : technicians) {
  		
    		deltaLat = technician.contactInformation.address.geoPoint.latitude - userLatitude;
    		deltaLng = technician.contactInformation.address.geoPoint.longtitude - userLongitude;
    		
    		distance = Math.pow(Math.sin(deltaLat/2), 2) + Math.cos(technician.contactInformation.address.geoPoint.latitude) * Math.cos(userLatitude) * Math.pow(Math.sin(deltaLng/2),2);
    		distance = 2 * Math.atan2( Math.sqrt(distance), Math.sqrt(1-distance) ) * 6373000; //last number is radius of earth, converts into [m] distance
    		System.out.println(technician.lastName+"|distance:"+distance);
    		if(closeTechnicians && distance>maxDistance)
    			continue;
    		else if(!closeTechnicians && distance<maxDistance)
    			continue;

    		TechnicianMap tm = new TechnicianMap();
    		tm.id = technician.id;
    		tm.name = technician.firstName + " "+ technician.lastName;
    		tm.longtitude = technician.contactInformation.address.geoPoint.longtitude;
    		tm.latitude = technician.contactInformation.address.geoPoint.latitude;
    		float sum = 0, i = 1;
    		for (Rating rat : technician.rating) {
				sum+=rat.rating;
				i++;
			}
    		tm.rating = sum/i;
    		techniciansMapList.add(tm);
		}
    	
    	Gson gsonHandler = new Gson();
    	return gsonHandler.toJson(techniciansMapList);
    }
    
   

}