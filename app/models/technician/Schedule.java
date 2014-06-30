package models.technician;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import models.Appointment;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@Embeddable
public class Schedule {
    /**
     * The scheduled appointments of the Actor
     */
    @ElementCollection
    @Expose
    @OneToMany
    List<Appointment> appointments;

    public Schedule(List<Appointment> appointments) {
        super();
        this.appointments = appointments;
    }
    
    
}
