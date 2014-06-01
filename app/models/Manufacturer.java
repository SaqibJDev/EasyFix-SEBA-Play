package models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.joda.time.DateTime;

import play.db.jpa.Model;

@Entity
public class Manufacturer extends Model implements Serializable{
	public String name;
	public String description;
	public String image;
	public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
//	@OneToMany(mappedBy = "manufacturer")
//	@PrimaryKeyJoinColumn
//	@OneToMany(mappedBy = "manufacturer") //(cascade = CascadeType.ALL)
//	public List<DeviceModel> deviceModels;
	//@JoinColumn(name="id", insertable = false, updatable = false)
//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}


	public Manufacturer(String name) {
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


	
//	public List<DeviceModel> getDeviceModels() {
//		return de;
//	}
//
//
//	public void setDeviceModels(List<DeviceModel> deviceModels) {
//		this.deviceModels = deviceModels;
//	}
	
}
