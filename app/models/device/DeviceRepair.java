package models.device;

import javax.persistence.Entity;

import org.joda.time.Duration;

import play.db.jpa.Model;

@Entity
public class DeviceRepair extends Model{

	public String name;
	public String displayName;
	public String description;
	public String image;
	public float price;
	public long repairTime;// cp: I changed it to Duration because we need it to calculate appointment duration
	public DeviceRepair(String name, String displayName, String description,
            String image, float price, long repairTime) {
        super();
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.repairTime = repairTime;
    }
    public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
}
