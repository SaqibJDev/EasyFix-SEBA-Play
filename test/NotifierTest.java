import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Actor;
import models.Location;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import notifiers.Mails;

import org.junit.Test;

import play.test.UnitTest;

/**
 * 
 * @author Saqib - saqib.javed87@gmail.com
 * 
 */

public class NotifierTest extends UnitTest {

	/*
	 * Send Email
	 */
	@Test
    public void SendEmail() {
		
        //assertNotNull(location);
        assertTrue(Mails.lostPassword(new Actor("Saqib", "Javed", "saqib.javed87@gmail.com", "blah blah", null)));
    }

}
