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

@Entity
public class DeviceModel extends Model{

	public String name;
	public DeviceModel(String name, String displayName, String description,
            String image, List<DeviceRepair> deviceRepairList) {
        super();
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.deviceRepairList = deviceRepairList;
    }

    public DeviceModel() {
        // TODO Auto-generated constructor stub
    }

    public String displayName;
	public String description;
	public String image;
	public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
	@OneToMany
	public List<DeviceRepair> deviceRepairList;
}
