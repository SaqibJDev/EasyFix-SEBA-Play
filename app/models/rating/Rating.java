package models.rating;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.db.jpa.Model;
@Entity
public class Rating extends Model{

    public long timestamp;
    
    public RatingValue ratingValue;
    
    public long customerId;
    
    public long technicianId;
}
