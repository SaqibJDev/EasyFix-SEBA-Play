import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Actor;
import models.Appointment;
import models.ContactInformation;
import models.Location;
import models.customer.Customer;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Schedule;
import models.technician.Technician;
import models.technician.WorkingHours;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;

import play.test.UnitTest;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void checkAvailabilityTest() {
        assertEquals(2, 1 + 1);
    }

}
