package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@Entity
public class ContactInformation extends Model{

    @OneToOne
    public Location address;
   
    /**
     * The telephone number - required
     */
    @Expose
    public String telephone;

    /**
     * The mobile number
     */
    @Expose
    public String mobile;

    /**
     * The email address - required
     */
    @Expose
    public String email;

    /**
     * Website of external technician - optional 
     */
    @Expose
    public String website;

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

}
