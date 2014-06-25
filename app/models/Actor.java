package models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Actor extends Model{

    /**
     * The first name of person
     */
    @Expose
    public String firstName;

    /**
     * The last name of person
     */
    @Expose
    public String lastName;
    
    @Expose
    public String email;
    
    @Expose
    public String password;
    
    @Expose
    public java.sql.Timestamp createdOn;
    @Expose
    public java.sql.Timestamp lastUpdated;

    /**
     * contact information of person
     */
    @Expose
    @OneToOne
    public ContactInformation contactInformation;

    public Actor() {
        super();
    }
    
    public Actor(String firstName, String lastName,
            ContactInformation contactInformation) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
    }
    
    public Actor(String firstName, String lastName, String email, String password,
            ContactInformation contactInformation) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactInformation = contactInformation;
    }

}
