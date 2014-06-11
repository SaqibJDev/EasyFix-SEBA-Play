package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * 
 * @author Chrysa Papapdaki - papadaki.chr@gmail.com
 * 
 */
@Entity
public class GeoPoint extends Model {

    public long latitude;
    public GeoPoint(long latitude, long longitude) {
        super();
        this.latitude = latitude;
        this.longtitude = longitude;
    }
    public long longtitude;
}
