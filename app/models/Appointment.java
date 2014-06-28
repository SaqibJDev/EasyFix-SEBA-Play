package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.device.DeviceRepair;
import models.rating.Rating;
import models.technician.Technician;

import org.joda.time.DateTime;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

/**
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
	public Timestamp dateTimeStart;

	/**
	 * The duration of the meeting
	 */
	// @Type(type = "org.joda.time.contrib.hibernate.PersistentDuration")
	public long duration;

	/**
	 * The location of the meeting which is the address of the customer
	 */
	@Expose
	@OneToOne
	public Location meetingPlace;

	public long customerId;

	public long technicianId;

	public long deviceRepairId;

	public long ratingId;

	/**
	 * Status of appointment as follows: 
	 * 0 - paid 1 - pending
	 */
	public int paymentStatus;

	public String getStartTime() {
		DateTime dt = new DateTime(this.dateTimeStart.getTime());
		return dt.getHourOfDay() + ":" + dt.getMinuteOfHour();
	}

	public String getEndTime() {
		DateTime dt = new DateTime(dateTimeStart.getTime() + duration);
		return dt.getHourOfDay() + ":" + dt.getMinuteOfHour();
	}

	public String getDate() {
		DateTime dt = new DateTime(this.dateTimeStart.getTime());
		return dt.getDayOfMonth() + "-" + dt.getMonthOfYear() + "-"
				+ dt.getYear();
	}
	
	public String getStatus(){
		return (paymentStatus == 0)? "paid":"pending";
	}

	public DeviceRepair getDeviceRepair(){
		return  (DeviceRepair) DeviceRepair
				.find("byId", this.deviceRepairId).fetch(1).get(0);
	}
	
	public Technician getTechnician(){
		return (Technician) Technician
		.find("byTechnicianId", this.technicianId).first();
	}
	
	public Rating getRating(){
		return (Rating) Rating
		.find("byId", this.ratingId).first();
	}
	/**
	 * not used for the moment The technician and the customer who will attend
	 * the meeting
	 */
	// @OneToMany
	// public List<Actor> attendees;
	// @Expose
	// public long customerId;

	public Appointment(Timestamp dateTimeStart, long duration,
			Location address, long customerId, long technicianId,
			long deviceRepairId) {
		super();
		this.dateTimeStart = dateTimeStart;
		this.duration = duration;
		this.meetingPlace = address;
		this.customerId = customerId;
		this.technicianId = technicianId;
		this.deviceRepairId = deviceRepairId;
	}

}
