package models;

import java.util.List;

import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Technician;

import org.joda.time.DateTime;
/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 *
 */
public class CheckAvailability {


    /**
     * Returns the list of available technicians for a specific timeslot to fix appointment
     * to fix specified repair.
     * It compares the city/zip field to check on location availability
     * It checks whether the technician is available for specific timeslot and is able to fix the repair.
     * @param location customer's location
     * @param appointmentDateTime
     * @param model devicemodel
     * @param repair
     * @return the list of available technicians to fix repair in specified location.
     */
    public List<Technician> getAvailableTechniciansForRepair (Location location, DateTime appointmentDateTime,DeviceModel model, DeviceRepair repair){
        //TODO
        return null;
    }
  
}
