package models.technician;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import models.Actor;
import models.Appointment;
import models.ContactInformation;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.rating.Rating;
import models.rating.RatingValue;

import com.google.gson.annotations.Expose;

/**
 * It is used for internal and external technicians
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@Entity
public class Technician extends Actor {

    /**
     * The title of occupation of technician
     */
    @Expose
    public String title;

    /**
     * Short description about technician's skills and competences
     */
    @Expose
    public String description;
    
    @OneToMany
    public List<Appointment> appointments; 

    /**
     * 
     * @param firstName
     * @param lastName
     * @param contactInformation
     * @param title
     * @param description
     * @param workingHours weekhours
     * @param image
     * @param isExternal
     * @param deviceModelList
     */
    public Technician(String firstName, String lastName,
            ContactInformation contactInformation, String title,
            String description, List<WorkingHours> workingHours, String image,
            boolean isExternal, List<DeviceModel> deviceModelList) {

        super(firstName, lastName, contactInformation);
        this.title = title;
        this.description = description;
        this.workingHours = workingHours;
        this.image = image;
        this.isExternal = isExternal;
        this.deviceModelList = deviceModelList;
    }

    public Technician() {
        // TODO Auto-generated constructor stub
    }

    public Technician(String firstName, String lastName,String email, String password,
            ContactInformation contactInformation) {
    	super(firstName, lastName, email, password, contactInformation);
	}

	/**
     * The specified working hours of technician
     */
    @OneToMany
    public List<WorkingHours> workingHours;

    /**
     * The image path of technician
     */
    @Expose
    public String image;
    
    /**
     * It defines whether the technician is external or internal
     */
    @Expose
    public boolean isExternal;

    /**
     * DeviceModels which can be repaired by technician
     */
    @ElementCollection
    @OneToMany
    public List<DeviceModel> deviceModelList;
    

    /**
     * The score of users' ratings on technician service
     */
    @OneToMany
    //public RatingValue ratingScore;
    public List<Rating> rating;
    
    public static List<Technician> findByAddress(String city) {

        return Technician.find(
                "select t from Technician t, Location l "
                        + "where t.isExternal = false and l.city like ?",
                "%" + city + "%").fetch();
    }
    
    public static List<Technician> findByGeoPoint(long latitude, long longtitude) {
        return null;
    }
    public static List<Technician> findTechniciansByIsExternal(
            boolean isExternal) {
        return Technician.find(
                "select t from Technician t "
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
