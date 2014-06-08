import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import models.Appointment;
import models.ContactInformation;
import models.Location;
import models.customer.Customer;
import models.dao.TechnicianDAO;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Schedule;
import models.technician.Technician;
import models.technician.WorkingHours;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;

import play.Logger;
import utility.GsonUtil;

public class TechnicianStorageTest extends TestCase{


    @Test
    public void testInsertTechnicians() {
        List<Technician> techs = new ArrayList<Technician>();

        // Internal technicians
        Technician inTech1 = createInternalTechnician("Joe", "Doe");
        techs.add(inTech1);
        Technician inTech2 = createInternalTechnician("Sebastian", "Maier");
        // Locations that technician can serve
        List<Location> locs = new ArrayList<Location>();
        locs.add(new Location("Munich", "80333"));
        locs.add(new Location("Zorneding", "85604"));
        inTech2.getContactInformation().setAdresses(locs);
        techs.add(inTech2);
        Technician inTech3 = createInternalTechnician("Marina", "Mavrikou");
        techs.add(inTech3);
        Technician inTech4 = createInternalTechnician("Achil", "Fil");
        techs.add(inTech4);
        Technician inTech5 = createInternalTechnician("Jane", "Fargo");
        techs.add(inTech5);
        Technician inTech6 = createInternalTechnician("Tim", "Smith");
        techs.add(inTech6);
        Technician inTech7 = createInternalTechnician("John", "Doe");
        techs.add(inTech7);
        // External technicians
        Technician exTech1 = createExternalTechnician("John", "Smith");
        techs.add(exTech1);
        Technician exTech2 = createExternalTechnician("Tina", "Jones");
        techs.add(exTech2);
        Technician exTech3 = createExternalTechnician("Demis", "Rousos");
        techs.add(exTech3);
        Technician exTech4 = createExternalTechnician("Sakis", "Taylor");
        techs.add(exTech4);
        Technician exTech5 = createExternalTechnician("Stayros", "Milos");
        techs.add(exTech5);

        TechnicianDAO dao = new TechnicianDAO();
        for (Technician tech : techs) {
            dao.add(tech);
        }
        
        assertNotNull(dao.getAllTechnicians());
    }

    public static Technician createExternalTechnician(String fname, String lname) {
        // construct technician object

        // set available working hours of technician
        // Map<Integer, List<Interval>> workingHoursMap = new HashMap<Integer,
        // List<Interval>>();
        List<Interval> hours = new ArrayList<Interval>();

        List<Object> weekHours = new ArrayList<Object>();
        // hours for Calendar.MONDAY
        hours.add(new Interval(new DateTime(2014, 1, 1, 10, 0), new DateTime(
                2014, 1, 1, 16, 0)));

        hours.add(new Interval(new DateTime(2014, 1, 1, 18, 0), new DateTime(
                2014, 1, 1, 20, 0)));
        weekHours.add(new WorkingHours(1, hours));
        // hours for Calendar.TUESDAY
        // hours.clear();
        hours.add(new Interval(new DateTime(2014, 1, 1, 14, 0), new DateTime(
                2014, 1, 1, 16, 0)));
        weekHours.add(new WorkingHours(2, hours));

        // set devicemodel list
        List<DeviceModel> modelList = new ArrayList<DeviceModel>();
        modelList.add(new DeviceModel());

        Logger.info("External tech created");
        return new Technician(fname, lname, new ContactInformation(
                "0874924687", "015987487", "info@fixit.de", new Location(
                        "Munich", "Giselastr", "12", "80321", "Germany"),
                "www.fixit.de"), "Smartphone repairing services", "iphone",
                weekHours, "logo1.jpeg", true, modelList);

    }

    public static Technician createInternalTechnician(String fname, String lname) {
        // construct technician object
        // set available working hours of technician
        // Map<Integer, List<Interval>> workingHoursMap = new HashMap<Integer,
        // List<Interval>>();
        List<Interval> hours = new ArrayList<Interval>();

        List<Object> weekHours = new ArrayList<Object>();
        // hours for Calendar.MONDAY
        hours.add(new Interval(new DateTime(2014, 1, 1, 10, 0), new DateTime(
                2014, 1, 1, 16, 0)));

        hours.add(new Interval(new DateTime(2014, 1, 1, 18, 0), new DateTime(
                2014, 1, 1, 20, 0)));
        weekHours.add(new WorkingHours(1, hours));
        // hours for Calendar.TUESDAY
        hours.clear();
        hours.add(new Interval(new DateTime(2014, 1, 1, 14, 0), new DateTime(
                2014, 1, 1, 16, 0)));
        weekHours.add(new WorkingHours(2, hours));

        // set devicemodel list
        List<DeviceModel> modelList = new ArrayList<DeviceModel>();
        modelList.add(new DeviceModel());
        // To set the appoint we need devicerepair time and attendees
        List<Appointment> apps = new ArrayList<Appointment>();
        // customer booked a repair with this technician

        // we need to first add customer to database and then add appointment
        Customer c = new Customer("Jane", "James", new ContactInformation(
                "087428748", "015324798", "jane@tum.de", new Location("Munich",
                        "Giselastr", "12", "80321", "Germany")), "comments");

        Appointment ap = new Appointment(new DateTime(2014, 1, 1, 10, 0),
                new DeviceRepair().getRepairTime(), c.getContactInformation()
                        .getAddress(), 1);
        apps.add(ap);

        // Locations that user can serve
        List<Location> locs = new ArrayList<Location>();
        locs.add(new Location("Munich", "80333"));
        locs.add(new Location("Garching", "85748"));

        Logger.info("Internal tech created");
        return new Technician(fname, lname, new ContactInformation(
                "087424837", "01587464798", fname+"@tum.de", locs), "Smartphone technician",
                "iphone", weekHours, "logo1.jpeg", new Schedule(apps), false,
                modelList);

    }
}
