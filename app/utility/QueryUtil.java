package utility;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Technician;

public class QueryUtil {

    public static List<Technician> findByLocationQuery(String location) {

        return Technician.find(
                "select t from Technician t, Location l "
                        + "where t.isExternal = false and l.city like ?",
                "%" + location + "%").fetch();
    }

    public static List<Technician> findTechniciansByIsExternal(
            boolean isExternal) {

        return Technician.find(
                "select t from Technician t, Location l "
                        + "where t.isExternal =" + isExternal)
                .fetch();
    }

    
    public static List<Technician> findInTechniciansByRepair(
            List<Technician> techInLocation, String dm, String repair) {
        Logger.info("guery parms for  findInTechniciansByRepair" + dm
                + " " + repair);
        List<Technician> repairTechnicians = new ArrayList<Technician>();
        if (techInLocation != null) {
            for (Technician tech : techInLocation) {
                List<DeviceModel> supportedModels = tech.deviceModelList;

                List<DeviceRepair> repairs = new ArrayList<DeviceRepair>();

                Logger.info("teeeech1" + tech.firstName);
                for (DeviceModel model : supportedModels) {

                    Logger.info("teeeech2 " + tech.firstName + " model "
                            + model.name + "=" + dm);
                    if (model.name == dm) {
                        repairs = model.deviceRepairList;
                        for (DeviceRepair deviceRepair : repairs) {
                            Logger.info("teeeech3" + tech.firstName + " r"
                                    + deviceRepair.name);
                            if (deviceRepair.name.equals(repair)) {
                                repairTechnicians.add(tech);
                                Logger.info("teeeech4" + tech.firstName);
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