import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

import utility.GsonUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mchange.util.AssertException;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
public class SerializarionTest {


    @Test
    public void testWorkingHours() {

        List<WorkingHours> weekHours = getWorkingHours();
        Type listType = new TypeToken<List<WorkingHours>>() {
        }.getType();
        String json = GsonUtil.createJson(weekHours);
        assertEquals(weekHours.size(), GsonUtil.fromJsonToList(json, listType)
                .size());

    }

    private List<WorkingHours> getWorkingHours() {
        List<Interval> hours = new ArrayList<Interval>();
        List<WorkingHours> weekHours = new ArrayList<WorkingHours>();
        // hours for Calendar.MONDAY
        hours.add(new Interval(new DateTime(2014, 1, 1, 10, 0), new DateTime(
                2014, 1, 1, 16, 0)));
        hours.add(new Interval(new DateTime(2014, 1, 1, 18, 0), new DateTime(
                2014, 1, 1, 20, 0)));
        weekHours.add(new WorkingHours(1, hours));
        return weekHours;
    }

    @Test
    public void testCustomer() {

        Customer c = new Customer("Jane", "James", new ContactInformation(
                "087428748", "015324798", "jane@tum.de", new Location("Munich",
                        "Giselastr", "12", "80321", "Germany")), "comments");
        
       String json = GsonUtil.createJson(c);
       Customer c1 = GsonUtil.fromJsonToObj(json, Customer.class);
       assertEquals(c.getFirstName(), c1.getFirstName());
    }
    
    @Test
    public void testTechnician() {
        
        // set devicemodel list
        List<DeviceModel> modelList = new ArrayList<DeviceModel>();
        modelList.add(new DeviceModel());
        
        Technician t =new Technician("abc", "cde", new ContactInformation("087424837",
                "01587464798",   "abc@tum.de", getLocs()),
                "Smartphone technician", "iphone", getWorkingHours(), "logo1.jpeg",
                new Schedule(getAppointments()), false, modelList);
        
       String json = GsonUtil.createJson(t);
       assertEquals(t.getDescription(), GsonUtil.fromJsonToObj(json,Technician.class).getDescription());
    }

    private List<Appointment> getAppointments() {
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
        return apps;
    }

    private List<Location> getLocs() {
        List<Location> locs = new ArrayList<Location>();
        locs.add(new Location("Munich", "80333"));
        locs.add(new Location("Garching", "85748"));
        return locs;
    }

}
