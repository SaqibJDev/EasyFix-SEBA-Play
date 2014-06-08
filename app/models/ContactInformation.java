package models;

import java.util.List;

import org.hibernate.annotations.Immutable;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@Immutable
public class ContactInformation {

    @Expose
    private Location address;

    /**
     * 
     * @param telephone
     * @param mobile
     * @param email
     * @param addresses
     * @param website
     */
    public ContactInformation(String telephone, String mobile, String email,
        List<Location> addresses) {
        super();
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.addresses = addresses;
    }
    /**
     * For external
     * @param telephone
     * @param mobile
     * @param email
     * @param address
     */
    public ContactInformation(String telephone, String mobile, String email,
            Location address, String website) {
            super();
            this.telephone = telephone;
            this.mobile = mobile;
            this.email = email;
            this.address = address;
            this.website = website;
        }
    
    /**
     * For customer
     * @param telephone
     * @param mobile
     * @param email
     * @param address
     */
    public ContactInformation(String telephone, String mobile, String email,
            Location address) {
            super();
            this.telephone = telephone;
            this.mobile = mobile;
            this.email = email;
            this.address = address;
        }
    /**
     * The telephone number - required
     */
    @Expose
    private String telephone;

    /**
     * The mobile number
     */
    @Expose
    private String mobile;

    /**
     * The email address - required
     */
    @Expose
    private String email;

    /**
     * Website of external technician - optional 
     */
    @Expose
    private String website;

    /**
     * The location of actor, for technician it is the location he/she can serve and for customer the
     * desirable meeting place
     */
    @Expose
    private List<Location> addresses;

    public String getTelephone() {
        return telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public List<Location> getAddresses() {
        return addresses;
    }
    public Location getAddress() {
        return address;
    }
    public void setAddress(Location address) {
        this.address = address;
    }
    public List<Location> setAdresses(List<Location> locs) {
        return this.addresses;
    }
}
