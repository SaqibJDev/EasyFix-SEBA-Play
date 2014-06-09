package models.customer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import models.Actor;
import models.Appointment;
import models.ContactInformation;

import com.google.gson.annotations.Expose;
/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 *
 */
@Entity
@Table(name = "customer")
public class Customer extends Actor {

    /**
     * Additional Information 
     */
//    @Expose
//    private String notes;
    
 //   @OneToMany
   // public List<Appointment> appointments; 
    
    public Customer(String firstName, String lastName,
            ContactInformation contactInformation) {
        super(firstName, lastName, contactInformation);
        //this.notes = notes;
    }
}
