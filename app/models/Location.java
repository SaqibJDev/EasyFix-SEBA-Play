package models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

/**
<<<<<<< HEAD
 * It corresponds to both location that technician can go and customer's
=======
 * It corrsponds to both location that technician can go and customer's
>>>>>>> df11bc212a6466e5ab54361ae08630b40b38178f
 * preferable place to meet
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
<<<<<<< HEAD
@Embeddable
=======
//@Embeddable
@Entity
>>>>>>> df11bc212a6466e5ab54361ae08630b40b38178f
public class Location extends Model {
	/**
	 * The name of the city, town, village or other community or delivery
	 * center.
	 */
	@Expose
	private String city;

	/**
	 * The name of street of town
	 */
	@Expose
	private String street;

	/**
	 * Street number
	 */
	@Expose
	private String streetNo;

	/**
	 * 
	 * @param city
	 * @param state
	 * @param street
	 * @param streetNo
	 * @param zip
	 * @param country
	 */
	public Location(String city, String street, String streetNo, String zip,
			String country) {
		super();
		this.city = city;
		this.street = street;
		this.streetNo = streetNo;
		this.zip = zip;
		this.country = country;
	}

	public Location(String city, String zip) {
		super();
		this.city = city;
		this.zip = zip;
	}

	/**
	 * A postal code designating a region defined by the postal service.
	 */
	@Expose
	private String zip;

	/**
	 * Country. ISO 3166 3 letter codes can be used in place of a full country
	 * name.
	 */
	@Expose
	private String country;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
