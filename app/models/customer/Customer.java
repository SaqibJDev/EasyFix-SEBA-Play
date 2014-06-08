package models.customer;

import javax.persistence.Entity;

import models.Actor;
import models.ContactInformation;

import com.google.gson.annotations.Expose;
/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 *
 */
@Entity
public class Customer extends Actor {

    /**
     * Additional Information 
     */
    @Expose
    private String notes;
    
    public Customer(String firstName, String lastName,
            ContactInformation contactInformation, String notes) {
        super(firstName, lastName, contactInformation);
        this.notes = notes;
    }
}
