package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.customer.Customer;
import models.device.DeviceRepair;
import models.technician.Technician;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Appointment extends Model {

    /**
     * The appointment time
     */
    public Timestamp dateTimeStart;

    /**
     * The duration of the meeting
     */
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

    public String templ;

    public Appointment(Date dateTimeStart, long duration,
            Location address, long customerId, long technicianId, long deviceRepairId) {
        super();
        //this.dateTimeStart = dateTimeStart;
        this.duration = duration;
        this.meetingPlace = address;
//        this.customerId = customerId;
//        this.technicianId = technicianId;
//        this.deviceRepairId = deviceRepairId;
    }


	public Appointment() {
		// TODO Auto-generated constructor stub
	}

}
