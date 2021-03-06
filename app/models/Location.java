package models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

/**
 * It corresponds to both location that technician can go and customer's
 * preferable place to meet
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@Entity
public class Location extends Model {
    /**
     * The name of the city, town, village or other community or delivery
     * center.
     */
    @Expose
    public String city;

    /**
     * The name of street of town
     */
    @Expose
    public String street;

    /**
     * Street number
     */
    @Expose
    public String streetNo;

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
        this.city = city;
        this.street = street;
        this.streetNo = streetNo;
        this.zip = zip;
        this.country = country;
    }

    public Location(String city, String zip) {
        this.city = city;
        this.zip = zip;
    }

    public Location(GeoPoint gp) {
        this.geoPoint = gp;
    }

    /**
     * A postal code designating a region defined by the postal service.
     */
    @Expose
    public String zip;

    public GeoPoint geoPoint;

    /**
     * Country. ISO 3166 3 letter codes can be used in place of a full country
     * name.
     */
    @Expose
    public String country;

}
