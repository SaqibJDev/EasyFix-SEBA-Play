package models.ajaxResponse;

public class TechnicianMap {
	public String name;
	public long id;
	public float latitude;
    public float longtitude;
    public float rating;
	public TechnicianMap(String name, long id, float latitude, float longtitude,
			float rating) {
		super();
		this.name = name;
		this.id = id;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.rating = rating;
	}
	public TechnicianMap() {}
    
    
}
