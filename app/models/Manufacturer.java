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
public class Manufacturer extends Model{

	public String name;
	public String displayName;
	public String description;
	public String image;
	public java.sql.Timestamp createdOn;
	public java.sql.Timestamp lastUpdated;
	
	@OneToMany
	public List<DeviceModel> deviceModels;
}
