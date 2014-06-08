package models.technician;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
import javax.persistence.Entity;
>>>>>>> df11bc212a6466e5ab54361ae08630b40b38178f

import org.joda.time.Interval;

import play.db.jpa.Model;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

/**
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 */
@Embeddable
<<<<<<< HEAD
=======
@Entity
>>>>>>> df11bc212a6466e5ab54361ae08630b40b38178f
public class WorkingHours extends Model{

    /**
     * Calendar.DAY_OF_WEEK
     */
    @Expose
    private int day;
    
    @Expose
    @ElementCollection
    @OneToMany
    private  List<Interval> hours;
    /**
     * Map of working hours, use Calendar.DAY_OF_WEEK to define integer and joda
     * period to define timeslots for that day
     */
  /*  @Expose
    @ElementCollection
    private final Map<Integer, List<Interval>> workingHours;

    public Map<Integer, List<Interval>> getWorkingHours() {
        return workingHours;
    }

    public WorkingHours(Map<Integer, List<Interval>> workingHours) {
        super();
        this.workingHours = workingHours;
    }*/
    public WorkingHours(int day, List<Interval> hours) {
        super();
        this.day = day;
        this.hours = hours;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public List<Interval> getHours() {
        return hours;
    }
    public void setHours(List<Interval> hours) {
        this.hours = hours;
    }

}
