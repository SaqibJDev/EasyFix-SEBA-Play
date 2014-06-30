package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import controllers.Secure.Security;
import models.Actor;
import models.device.DeviceRepair;
import models.technician.Technician;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class ExternalTechnicians extends Application {

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
    

}