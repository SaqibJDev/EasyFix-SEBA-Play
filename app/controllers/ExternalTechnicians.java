package controllers;

import play.*;
import play.mvc.*;
import utility.QueryUtil;

import java.util.*;

import models.*;
import models.device.DeviceRepair;
import models.technician.Technician;

public class ExternalTechnicians extends Controller {

    public static void show(String deviceModel, String repair) {
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
            render(exTechnicians, deviceRepair);
        } else
            render();
    }
    

    public static void showAll() {
        List<Technician> exTechnicians = QueryUtil.findTechniciansByIsExternal(true);
        
        if (exTechnicians != null) {

            Logger.info(exTechnicians.get(0).description);
            Collections.sort(exTechnicians, new Comparator<Technician>() {
                public int compare(Technician o1, Technician o2) {
                    return o1.lastName.compareTo(o2.lastName);
                }
            });
            render(exTechnicians);
        } else
            render();
    }

}