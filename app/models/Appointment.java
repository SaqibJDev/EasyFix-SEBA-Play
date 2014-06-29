package models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.device.DeviceRepair;
import models.rating.Rating;
import models.technician.Technician;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	 * Status of payment
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

	public String getDuration(){
		Duration duration = new Duration(this.duration);
		return duration.getStandardMinutes()+" min";
	}
	public String getDate() {
		DateTime dt = new DateTime(this.dateTimeStart.getTime());
		DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
		return dt.toString(fmt);
	}
	
	public String getStatus(){
		return (paymentStatus == PaymentStatus.PAID.getIndex())? "paid":"pending";
	}

	public DeviceRepair getDeviceRepair(){
		return  (DeviceRepair) DeviceRepair
				.find("byId", this.deviceRepairId).fetch(1).get(0);
	}
	
	public Technician getTechnician(){
		return (Technician) Technician
		.find("byId", this.technicianId).first();
	}
	
	public Rating getRating(){
		Rating rating = (Rating) Rating
		.find("byId", this.ratingId).first();
		return (rating == null)?new Rating(0): rating;
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
