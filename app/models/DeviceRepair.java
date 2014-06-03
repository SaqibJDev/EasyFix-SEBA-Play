package models;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.joda.time.DateTime;

import play.db.jpa.Model;

@Entity
public class DeviceRepair extends Model{

	public String name;
	public String displayName;
	public String description;
	public String image;
	public float price;
	public String repairTime;
	public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
}
