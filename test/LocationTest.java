import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Location;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import org.junit.Test;

import play.test.UnitTest;

/**
 * 
 * @author Saqib - saqib.javed87@gmail.com
 * 
 */

public class LocationTest extends UnitTest {

	/*
	 * To test if database is connected and table exits for Location
	 */
	@Test
    public void retrieveLocationList() {
        List<Location> location =  Location.findAll();
        assertNotNull(location);
    }
	
	/*
	 * Creating Location
	 */
	@Test
    public void CreateSimpleLocation() {
		Location location = new Location("Munich", "Felsennelkenenger", "115", "80333", "Germany").save();
		assertNotNull(location);
        assertTrue(location.id > 0);
        
		location = new Location("Garching", "Garching Str.", "25", "80314", "Germany").save();
        assertNotNull(location);
        assertTrue(location.id > 0);
    }

}
