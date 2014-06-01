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

//@Entity
public class DeviceRepair /*extends Model*/{
//	@Column(name="ID", insertable=false, updatable=false)
//	public Long id;
	@Id @GeneratedValue
	public Long id;
	public String name;
	public String description;
	public String image;
	public float price;
	public String repairTime;
	public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
	public DeviceModel deviceModel;
	
	//@JoinColumn(name="id", insertable = false, updatable = false)
//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}


	public DeviceRepair(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public Timestamp getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}


	public Timestamp getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getRepairTime() {
		return repairTime;
	}


	public void setRepairTime(String repairTime) {
		this.repairTime = repairTime;
	}


	public DeviceModel getDeviceModel() {
		return deviceModel;
	}


	public void setDeviceModel(DeviceModel deviceModel) {
		this.deviceModel = deviceModel;
	}
	
}
