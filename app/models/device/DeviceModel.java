package models.device;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

import play.db.jpa.Model;

/*
 * Device model represents the phone model belongs to a company. e.g. iPhone 4 is a device model for company Apple
 * We store device name, images, description and display name with which it will appear on our web site
 * It have one to many relationship with device repair which means we may offer many different repairs for a single device model  
 */
@Entity
public class DeviceModel extends Model{

	public String name;
	public String displayName;
	public String description;
	public String image;
	public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
	@OneToMany
	public List<DeviceRepair> deviceRepairList;
	

    public DeviceModel() {
    }

    public DeviceModel(String name, String displayName, String description,
			String image, Timestamp createdOn, Timestamp lastUpdated, List<DeviceRepair> deviceRepairList) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.image = image;
		this.createdOn = createdOn;
		this.lastUpdated = lastUpdated;
		this.deviceRepairList = deviceRepairList;
	}


}
