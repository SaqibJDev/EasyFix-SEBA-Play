package models.rating;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.customer.Customer;
import models.technician.Technician;
import play.db.jpa.Model;
@Entity
public class Rating extends Model{

    public Rating(int i) {
		this.rating = i;
	}

	public long timestamp;
    /**
     * rating value - number of stars of customer for the selected technician for repair
     */
    public int rating;

	/**
     * User feedback on repair service
     */
    public String comment;
    @OneToOne
    public Customer customer;
    
    @OneToOne
    public Technician technician;
}
