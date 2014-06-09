package models.technician;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import models.Actor;
import models.ContactInformation;
import models.device.DeviceModel;

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

    /**
     * The specified working hours of technician
     */
    @OneToMany//(cascade=CascadeType.ALL)
    public List<WorkingHours> workingHours;

    /**
     * The image path of technician
     */
    @Expose
    public String image;

    /**
     * The schedule of technician
     */
    // @Expose
    // @Embedded
    // @OneToOne
    // public Schedule schedule;
    /*
     * @ElementCollection
     * @Expose
     * @OneToMany public List<Appointment> appointments;
     */
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

}
