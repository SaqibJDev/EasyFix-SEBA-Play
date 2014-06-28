package models.device;

import java.sql.Timestamp;

import javax.persistence.Entity;

import org.joda.time.Duration;

import play.db.jpa.Model;

/*
 * Device Repair represents the repair service we offer against specific device model of a Manufacturer
 * Other than usual attributes, it also has price (float) and duration of time it takes to perform repair (long)
 */
@Entity
public class DeviceRepair extends Model{

	public String name;
	public String displayName;
	public String description;
	public String image;
	public float price;
	public long repairTime;// cp: I changed it to Duration because we need it to calculate appointment duration
    public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
	public DeviceRepair(){}

	public DeviceRepair(String name, String displayName, String description,
			String image, float price, long repairTime, Timestamp createdOn,
			Timestamp lastUpdated) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.image = image;
		this.price = price;
		this.repairTime = repairTime;
		this.createdOn = createdOn;
		this.lastUpdated = lastUpdated;
	}
	
	/**
	 * in Germany vat rate is 19 percent
	 * @return vat 
	 */
	public float getVAT(){
		return  Math.round((price * 19f / 100f));
	}
	
	/**
	 * 
	 * @return price without vat
	 */
	public float getPriceWithoutVAT(){
		return  (price - getVAT());
	}
}
