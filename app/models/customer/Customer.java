package models.customer;

import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.PrimaryKeyJoinColumn;
=======
>>>>>>> df11bc212a6466e5ab54361ae08630b40b38178f
import javax.persistence.Table;

import models.Actor;
import models.ContactInformation;

import com.google.gson.annotations.Expose;
/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 *
 */
@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "actor_id")
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
