package models.rating;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.customer.Customer;
import models.technician.Technician;
import play.db.jpa.Model;
@Entity
public class Rating extends Model{

    public long timestamp;
    public int rating;
    @OneToOne
    public Customer customer;
    
    @OneToOne
    public Technician technician;
    //public RatingValue ratingValue;
    
    //public long customerId;
    
    //public long technicianId;
}
