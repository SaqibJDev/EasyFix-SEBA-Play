package models;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import models.customer.Customer;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.google.gson.annotations.Expose;

/**
 * Immutable
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 */
@Embeddable
public class Appointment {

    /**
     * The appointment time
     */
    @Expose
    private final DateTime dateTimeStart;

    /**
     * The duration of the meeting
     */
    @Expose
    private final Duration duration;


    /**
     * The location of the meeting which is the address of the customer
     */
    @Expose
    private final Location meetingPlace;

    /**
    * not used for the moment
     * The technician and the customer who will attend the meeting
     */
   // @OneToMany
 //   private final List<Actor> attendees;
    @Expose
    private final long customerId;

    public Appointment(DateTime dateTimeStart, Duration duration,
            Location address, long customerId) {
        super();
        this.dateTimeStart = dateTimeStart;
        this.duration = duration;
        this.meetingPlace = address;
        this.customerId = customerId;
    }

}
