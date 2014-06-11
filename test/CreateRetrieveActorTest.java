import java.util.ArrayList;
import java.util.List;

import models.ContactInformation;
import models.Interval;
import models.Location;
import models.customer.Customer;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Technician;
import models.technician.WorkingHours;

import org.joda.time.DateTime;
import org.junit.Test;

import play.test.UnitTest;


public class CreateRetrieveActorTest extends UnitTest {
    @Test
    public void createAndRetrieveCustomer() {

        Location l = Location.find("byZip", "80333").first();
        Customer c = new Customer("Jane", "Roe", new ContactInformation(
                "010394344", "w487937", "jane@row.com", l, "s")).save();

        // Test
        assertNotNull(c);
        assertEquals("Jane", c.firstName);
    }

    @Test
    public void createAndRetrieveTechnician() {

        // location
        Location l = new Location("garching", "82333").save();

        // 0 to 6, where 0 is Sunday, 1 is Monday,
        List<Interval> hours = new ArrayList<Interval>();

        List<WorkingHours> weekHours = new ArrayList<WorkingHours>();
        // create model.interval using joda getmillies
        Interval interval = new Interval(
                new DateTime(2014, 1, 1, 10, 0).getMillis(), new DateTime(2014,
                        1, 1, 16, 0).getMillis());
        hours.add(interval);
        interval = new Interval(new DateTime(2014, 1, 1, 18, 0).getMillis(),
                new DateTime(2014, 1, 1, 20, 0).getMillis());
        hours.add(interval);

        // 0 to 6, where 0 is Sunday, 1 is Monday,
        new WorkingHours(1, hours);
        new WorkingHours(2, hours);
        new WorkingHours(3, hours);
        new WorkingHours(4, hours);
        new WorkingHours(5, hours);

        weekHours = WorkingHours.findAll();
        new Technician("John", "Tith", new ContactInformation("010394344",
                "w487937", "john@example.com", l, "s"), "smartphone expert",
                "broken screens", weekHours, "logo1.jpeg", false,  createDeviceModelList()).save();

        // Retrieve the user with title address expert
        Technician t = Technician.find("byTitle", "smartphone expert")
                .first();

        // Test
        assertNotNull(t);
        assertEquals("John", t.firstName);
    }
    

    private List<DeviceModel> createDeviceModelList() {
        List<DeviceModel> models = new ArrayList<DeviceModel>();
        List<DeviceRepair> repairs = new ArrayList<DeviceRepair>();
        DeviceRepair r = new DeviceRepair("screen2", "screen", "", "", 50, 600000000, null,null).save();
        repairs.add(r);
        DeviceModel model = new DeviceModel("iphone6", "iphone6", "description", "image", null, null, repairs).save();

        models.add(model);
        
        return models;
    }

    
}
    