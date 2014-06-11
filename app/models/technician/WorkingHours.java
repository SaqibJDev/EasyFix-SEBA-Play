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
@Entity
public class WorkingHours extends Model{

    /**
     * Calendar.DAY_OF_WEEK
     */
    public int day;
    @Embedded
    public List<Interval> hours;

    /**
     * Map of working hours, use Calendar.DAY_OF_WEEK to define integer and joda
     * period to define timeslots for that day
     */

    public WorkingHours(int day, List<Interval> hours) {
        super();
        this.day = day;
        this.hours = hours;
    }
}
