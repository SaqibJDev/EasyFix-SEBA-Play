package models.technician;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import models.Interval;
import play.db.jpa.Model;

/**
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 */
//@Embeddable
@Entity
public class WorkingHours extends Model{

    /**
     * Calendar.DAY_OF_WEEK
     */
    public int day;
    @Embedded
    //@OneToMany//(cascade=CascadeType.ALL)
    public List<Interval> hours;
    //@ElementCollection
    //@OneToMany
    //private  List<Interval> hours;
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
}
