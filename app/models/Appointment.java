package models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.customer.Customer;
import models.device.DeviceRepair;
import models.technician.Technician;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

/**
 * Immutable
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 */
// @Embeddable
@Entity
public class Appointment extends Model {

    /**
     * The appointment time
     */
    // @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    public Date dateTimeStart;

    /**
     * The duration of the meeting
     */
    // @Type(type = "org.joda.time.contrib.hibernate.PersistentDuration")
    public long duration;

    /**
     * The location of the meeting which is the address of the customer
     */
    
    @OneToOne
    public Location meetingPlace;

    @OneToOne
    public Customer customer;

    @OneToOne
    public Technician technician;

    @OneToOne
    public DeviceRepair deviceRepair;


    public Appointment(Date dateTimeStart, long duration,
            Location address, long customerId, long technicianId, long deviceRepairId) {
        super();
        this.dateTimeStart = dateTimeStart;
        this.duration = duration;
        this.meetingPlace = address;
//        this.customerId = customerId;
//        this.technicianId = technicianId;
//        this.deviceRepairId = deviceRepairId;
    }

}
