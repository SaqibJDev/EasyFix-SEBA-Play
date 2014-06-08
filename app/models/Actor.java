package models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
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
    private String firstName;

    public Actor() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Expose
    public java.sql.Timestamp createdOn;
    @Expose
    public java.sql.Timestamp lastUpdated;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * The last name of person
     */
    @Expose
    private String lastName;

    /**
     * contact information of person
     */
    @Expose
    @OneToOne
<<<<<<< HEAD
    @Embedded
=======
>>>>>>> df11bc212a6466e5ab54361ae08630b40b38178f
    private ContactInformation contactInformation;

    public Actor(String firstName, String lastName,
            ContactInformation contactInformation) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
    }

}
