package models.customer;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.Actor;
import models.ContactInformation;
import models.PaymentInformation;
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
    @OneToOne
   public PaymentInformation paymentInformation;
    
    public Customer(String firstName, String lastName, String email, String password,
            ContactInformation contactInformation, PaymentInformation payinfo) {
        super(firstName, lastName,email, password, contactInformation);
        this.paymentInformation = payinfo;
    }
}
