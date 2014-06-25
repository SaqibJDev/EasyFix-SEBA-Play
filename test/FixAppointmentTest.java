import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.Appointment;
import models.ContactInformation;
import models.GeoPoint;
import models.Interval;
import models.Location;
import models.customer.Customer;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Technician;
import models.technician.WorkingHours;

import org.joda.time.DateTime;
import org.junit.Test;

import play.Logger;
import play.test.UnitTest;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
public class FixAppointmentTest extends UnitTest {

    @Test
    public void createAppointmentSimple() {
        createAndRetrieveCustomer();
        createAndRetrieveTechnician("Tim");
        new DeviceRepair("screen2", "screen", "", "", 80, 600000000, null, null).save();
        Technician john = Technician.find("byLastName", "Smith").first();
        DeviceRepair d = DeviceRepair.find("byDisplayName", "screen").first();
        Customer c = (Customer) Customer.findAll().get(0);
        Appointment app = new Appointment(new Date(new DateTime().getMillis()),
                d.repairTime, c.contactInformation.address, c.id, john.id, d.id)
                .save();
        assertEquals(c.contactInformation.address, app.meetingPlace);
    }

    private List<DeviceModel> createDeviceModelList() {
        List<DeviceModel> models = new ArrayList<DeviceModel>();
        List<DeviceRepair> repairs = new ArrayList<DeviceRepair>();
        DeviceRepair r = new DeviceRepair("screen2", "screen", "", "", 50,
                600000000, null, null).save();
        repairs.add(r);
        DeviceModel model = new DeviceModel("iphone6", "iphone6",
                "description", "image", null, null, repairs).save();
        models.add(model);
        return models;
    }

    @Test
    public void modelRepairExTechnicianByRepairAndLocationTest() {

        createAndRetrieveTechnician("chrysa");
        List<Technician> techs = Technician.findByGeoPoint(122,123);//Technician.findByAddressQuery("garching");

        assertNotSame(techs.size(), 0);
        Logger.info("sizeeee"+techs.size());
        techs = Technician.findTechniciansByRepair(techs,
                createDeviceModelList().get(0).name, "screen2");

        // Test
        assertNotSame(techs.size(), 0);
        assertEquals(techs.get(0).deviceModelList.get(0).name, "iphone6");
    }

    public void createAndRetrieveTechnician(String fname) {

        // location
        Location l = new Location(new GeoPoint(122,123)).save();

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
        new Technician(fname, "Smith", new ContactInformation("010394344",
                "w487937", "john@example.com", l, "s"), "smartphone expert",
                "broken screens", weekHours, "logo1.jpeg", false,
                createDeviceModelList()).save();

        // Retrieve the user with title address expert
        Technician bob = Technician.find("byTitle", "smartphone expert")
                .first();
    }

    public void createAndRetrieveCustomer() {

        Location l = Location.find("byZip", "80333").first();
        Customer c = new Customer("James", "Roe", "james@roe.com", "jamesroe", new ContactInformation(
                "010394344", "w487937", "jane@row.com", l, "s")).save();

    }
}
