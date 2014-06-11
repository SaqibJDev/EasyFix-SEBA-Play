package controllers;

import play.*;
import play.mvc.*;
import utility.QueryUtil;

import java.util.*;

import models.*;
import models.device.DeviceRepair;
import models.technician.Technician;

public class ExternalTechnicians extends Controller {

	/*
	 * Show external technicians who offer repair services for selected device model and repair
	 */
    public static void show(String maker, String deviceModel, String repair) {
        List<Technician> exTechnicians = QueryUtil.findTechniciansByRepair(
                QueryUtil.findTechniciansByIsExternal(true), deviceModel,
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
        List<Technician> exTechnicians = QueryUtil.findTechniciansByIsExternal(true);
        
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
        List<Technician> exTechnicians = QueryUtil.findTechniciansByIsExternal(true);
        
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