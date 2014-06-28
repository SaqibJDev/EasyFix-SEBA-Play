package models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * 
 * @author Chrysa Papapdaki - papadaki.chr@gmail.com
 * 
 */
@Embeddable
public class GeoPoint {

    public float latitude;
    public float longtitude;
    
    public GeoPoint(float latitude, float longitude) {
        super();
        this.latitude = latitude;
        this.longtitude = longitude;
    }
    
}
