package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@Entity
public class Actor extends Model {

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
    private ContactInformation contactInformation;

    public Actor(String firstName, String lastName,
            ContactInformation contactInformation) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
    }

}
