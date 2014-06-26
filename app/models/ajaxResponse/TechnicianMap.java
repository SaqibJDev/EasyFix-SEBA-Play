package models.ajaxResponse;

public class TechnicianMap {
	public String name;
	public long id;
	public long latitude;
    public long longtitude;
    public float rating;
	public TechnicianMap(String name, long id, long latitude, long longtitude,
			float rating) {
		super();
		this.name = name;
		this.id = id;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.rating = rating;
	}
    
    
}
