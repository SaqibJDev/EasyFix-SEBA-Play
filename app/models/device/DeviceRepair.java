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
	public Duration repairTime;// cp: I changed it to Duration because we need it to calculate appointment duration
	public DeviceRepair(String name, String displayName, String description,
            String image, float price, Duration repairTime) {
        super();
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.repairTime = repairTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public DeviceRepair() {
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public Duration getRepairTime() {
        return repairTime;
    }
    public void setRepairTime(Duration repairTime) {
        this.repairTime = repairTime;
    }
    public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
}
