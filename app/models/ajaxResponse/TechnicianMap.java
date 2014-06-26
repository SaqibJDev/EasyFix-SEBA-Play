package models.ajaxResponse;

public class TechnicianMap {
	public String name;
	public long id;
	public float latitude;
    public float longitude;
    public float rating;
	public TechnicianMap(String name, long id, float latitude, float longitude,
			float rating) {
		super();
		this.name = name;
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.rating = rating;
	}
	public TechnicianMap() {
		// TODO Auto-generated constructor stub
	}
    
    
}
