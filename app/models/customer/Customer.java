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

    private long customerId;
    /**
     * Additional Information 
     */
//    @Expose
//    private String notes;
    
 //   @OneToMany
   // public List<Appointment> appointments; 
    
    public Customer(String firstName, String lastName, String email, String password,
            ContactInformation contactInformation) {
        super(firstName, lastName,email, password, contactInformation);
        //this.notes = notes;
    }
}
