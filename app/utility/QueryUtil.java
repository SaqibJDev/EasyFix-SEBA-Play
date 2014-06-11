package utility;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Technician;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
public class QueryUtil {

    public static List<Technician> findByAddress(String city) {

        return Technician.find(
                "select t from Technician t, Location l "
                        + "where t.isExternal = false and l.city like ?",
                "%" + city + "%").fetch();
    }
    
    public static List<Technician> findByGeoPoint(long latitude, long longtitude) {
        return null;
//TODO
    }


    public static List<Technician> findTechniciansByIsExternal(
            boolean isExternal) {
        return Technician.find(
                "select t from Technician t, Location l "
                        + "where t.isExternal =" + isExternal).fetch();
    }

    public static List<Technician> findTechniciansByRepair(
            List<Technician> technicians, String dm, String repair) {
        List<Technician> repairTechnicians = new ArrayList<Technician>();
        if (technicians != null) {
            for (Technician tech : technicians) {

                List<DeviceModel> supportedModels = tech.deviceModelList;
                List<DeviceRepair> repairs = new ArrayList<DeviceRepair>();
                for (DeviceModel model : supportedModels) {

                    if (model.name.equalsIgnoreCase(dm)) {
                        repairs = model.deviceRepairList;
                        for (DeviceRepair deviceRepair : repairs) {
                            if (deviceRepair.name.equals(repair)) {
                                repairTechnicians.add(tech);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return repairTechnicians;
    }
    
    

}