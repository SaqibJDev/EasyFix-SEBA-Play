package models;

import javax.persistence.Embeddable;

import com.google.gson.annotations.Expose;

/**
 * It corrsponds to both location that technician can go and customer's preferable place to meet
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 *
 */
@Embeddable
public class Location {
    /**
     * The name of the city, town, village or other community or delivery center.
     */
    @Expose
    protected String city;
    
    /**
     * The name of street of town
     */
    @Expose
    protected String street;
    
    /**
     * Street number
     */
    @Expose
    protected String streetNo;
    

    /**
     * 
     * @param city
     * @param state
     * @param street
     * @param streetNo
     * @param zip
     * @param country
     */
    public Location(String city, String street, String streetNo,
            String zip, String country) {
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
    protected String zip;

    /**
     * Country. ISO 3166 3 letter codes can be used in place of a full country name.
     */
    @Expose
    protected String country;

}
