package models.technician;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.google.gson.annotations.Expose;

import models.Appointment;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
@Embeddable
public class Schedule {
    private long technicianId;
    /**
     * The scheduled appointments of the Actor
     */
    @ElementCollection
    @Expose
    List<Appointment> appointments;

    public Schedule(List<Appointment> appointments) {
        super();
        this.appointments = appointments;
    }
    
    
}
