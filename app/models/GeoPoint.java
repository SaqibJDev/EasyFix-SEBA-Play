package models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * 
 * @author Chrysa Papapdaki - papadaki.chr@gmail.com
 * 
 */
//@Entity
@Embeddable
public class GeoPoint {

    public long latitude;
    public long longtitude;
    
    public GeoPoint(long latitude, long longitude) {
        super();
        this.latitude = latitude;
        this.longtitude = longitude;
    }
    
}
