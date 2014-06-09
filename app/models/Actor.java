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

    public Actor() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Expose
    public java.sql.Timestamp createdOn;
    @Expose
    public java.sql.Timestamp lastUpdated;

  
    /**
     * The last name of person
     */
    @Expose
    public String lastName;

    /**
     * contact information of person
     */
    @Expose
    //@ManyToOne
    //@OneToOne
    @Embedded
    public ContactInformation contactInformation;

    public Actor(String firstName, String lastName,
            ContactInformation contactInformation) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
    }

}
