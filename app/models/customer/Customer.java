package models.customer;

import javax.persistence.Entity;

import models.Actor;
import models.ContactInformation;
import models.Location;
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
    private String notes;
    
    public Customer(String firstName, String lastName,
            ContactInformation contactInformation, String notes) {
        super(firstName, lastName, contactInformation);
        this.notes = notes;
    }
}
