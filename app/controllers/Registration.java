package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import notifiers.Mails;
import models.Actor;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.device.Manufacturer;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;


public class Registration extends Application {

	/*
	 * Shows list of all manufacturer from database
	 * Sorted on Name
	 */
    public static void index() {
//        List<Manufacturer> manufacturers = Manufacturer.findAll();
//        Collections.sort(manufacturers, new Comparator<Manufacturer>() {
//            public int compare(Manufacturer o1, Manufacturer o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });

        render();
    }

    public static void register(String email, String password, String name, String confpassword){
    	System.out.println("email: "+email+ " | password: "+password+" | Name: "+name);
    	validation.required(name);
    	validation.required(email);
    	validation.required(password);
    	validation.equals("password",password,"confpassword",confpassword);
        
        if(validation.hasErrors()) {
            for(play.data.validation.Error error : validation.errors()) {
                System.out.println(error.message());
            }
        }else{
        	Actor actor = new Actor(name, null, email, password, null);
        	if(actor.create()){
        		Mails.welcome(actor);
        		redirect("/registerConfirm");
        	};
        }
        
        render();
    }
    
    public static void registerConfirm(){
    	render();
    }
    
    
    
    
    
    
    
    /*
     * Show All available device for a given Manufacturer
     */
    public static void manufacturer(String maker) {
        Manufacturer manufacturer = (Manufacturer) Manufacturer
                .find("byName", maker).fetch().get(0);
        if (manufacturer != null) {
            List<DeviceModel> devices = manufacturer.deviceModels;
            Collections.sort(devices, new Comparator<DeviceModel>() {
                public int compare(DeviceModel o1, DeviceModel o2) {
                    return o1.name.compareTo(o2.name);
                }
            });
            render(maker, devices);
        } else {
            render();
        }
    }

    /*
     * Show list of all available repairs against given Manufacturer's device Model
     */
    public static void deviceModelRepairList(String maker, String deviceModel) {

        Manufacturer manufacturer = (Manufacturer) Manufacturer
                .find("byName", maker).fetch().get(0);
        List<DeviceRepair> deviceRepairList = new ArrayList<DeviceRepair>();
        if (manufacturer != null) {

            List<DeviceModel> devices = manufacturer.deviceModels;
            for (DeviceModel deviceModelLocal : devices) {
                if (deviceModelLocal.name.equals(deviceModel)) {
                    deviceRepairList = deviceModelLocal.deviceRepairList;
                }
            }
            Collections.sort(deviceRepairList, new Comparator<DeviceRepair>() {
                public int compare(DeviceRepair arg0, DeviceRepair arg1) {
                    return arg0.name.compareTo(arg1.name);
                }
            });
            render(maker, deviceModel, deviceRepairList);
        } else {
            render();
        }
    }

    /*
     * Shows details of selected device repair
     */
    public static void deviceModelRepairDetails(String maker,
            String deviceModel, String repair) {
        DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
                .find("byName", repair).fetch(1).get(0);
        if (deviceRepair != null) {
            render(maker, deviceModel, deviceRepair, repair);
        } else {
            render();
        }

    }


}
