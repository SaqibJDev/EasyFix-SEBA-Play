package models;

import javax.persistence.Embeddable;

import play.db.jpa.Model;

@Embeddable
public class Interval extends Model{

    /**
     * start millis from joda.Interval
     */
    public long startMillis;

    /**
     * end millis from joda.Interval
     */
    public long endMillis;

    public Interval(long startMillis, long endMillis) {
        super();
        this.startMillis = startMillis;
        this.endMillis = endMillis;
    }
}
