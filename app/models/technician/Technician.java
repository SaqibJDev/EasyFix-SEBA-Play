package models.technician;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    private String title;

    /**
     * Short description about technician's skills and competences
     */
    @Expose
    private String description;

    /**
     * Creates obect of internal technician
     * 
     * @param firstName
     * @param lastName
     * @param contactInformation
     * @param title
     * @param description
     * @param list
     * @param image
     * @param schedule
     * @param isExternal
     * @param deviceModelList
     */
    public Technician(String firstName, String lastName,
            ContactInformation contactInformation, String title,
            String description, List<WorkingHours> list, String image,
            Schedule schedule, boolean isExternal,
            List<DeviceModel> deviceModelList) {

        super(firstName, lastName, contactInformation);
        this.title = title;
        this.description = description;
        this.workingHours = list;
        this.image = image;
        this.schedule = schedule;
        this.isExternal = isExternal;
        this.deviceModelList = deviceModelList;
    }

    /**
     * Creates object of external technician
     * 
     * @param firstName
     * @param lastName
     * @param contactInformation
     * @param title
     * @param description
     * @param workingHours
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
    @Expose
    @ElementCollection
    @OneToMany
    private List<WorkingHours> workingHours;

    /**
     * The image path of technician
     */
    @Expose
    private String image;

    /**
     * The schedule of technician
     */
    @Expose
    @Embedded
    @OneToOne
    private Schedule schedule;

    /**
     * It defines whether the technician is external or internal
     */
    @Expose
    private boolean isExternal;

    /**
     * DeviceModels which can be repaired by technician
     */
    @ElementCollection
    @OneToMany
    public List<DeviceModel> deviceModelList;

    public String getTitle() {
        // TODO Auto-generated method stub
        return this.title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHours> workingHours) {
        this.workingHours = workingHours;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean isExternal) {
        this.isExternal = isExternal;
    }

    public List<DeviceModel> getDeviceModelList() {
        return deviceModelList;
    }

    public void setDeviceModelList(List<DeviceModel> deviceModelList) {
        this.deviceModelList = deviceModelList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setID(int id) {
        this.id = (long) id;
        
    }


}
