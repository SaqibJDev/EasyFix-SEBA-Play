import java.util.ArrayList;
import java.util.List;

import models.ContactInformation;
import models.Interval;
import models.Location;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.technician.Technician;
import models.technician.WorkingHours;

import org.joda.time.DateTime;
import org.junit.Test;

import play.db.jpa.JPABase;
import play.test.UnitTest;
import utility.QueryUtil;


public class ManufacturerTest extends UnitTest {

    @Test
    public void retrieveExternalTechnician() {
        createAndRetrieveTechnician();
        List<Technician> exTechs =  QueryUtil.findTechniciansByIsExternal(true);
        assertNotNull(exTechs);

         exTechs = QueryUtil.findTechniciansByRepair(exTechs, "iphone6", "screen2");
        assertNotNull(exTechs);
    }
    

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
       ContactInformation ci = new ContactInformation("010394344",
                "w487937", "john@example.com", l, "s").save();
        new Technician("Marcus", "Dim",ci , "smartphone expert",
                "broken screens", weekHours, "tech1.jpg", true,  createDeviceModelList()).save();

        // Retrieve the user with title address expert
        Technician t = Technician.find("byTitle", "smartphone expert")
                .first();
    }
    

    private List<DeviceModel> createDeviceModelList() {
        List<DeviceModel> models = new ArrayList<DeviceModel>();
        List<DeviceRepair> repairs = new ArrayList<DeviceRepair>();
        DeviceRepair r = new DeviceRepair("screen2", "screen", "", "", 50, 600000000);
        r.create();
        repairs.add((DeviceRepair) DeviceRepair.find("byName", "screen2").fetch().get(0));
        System.out.println("Repairs size"+repairs.size());
        DeviceModel model = new DeviceModel("iphone6", "iphone6", "description", "image", repairs);
        //model.deviceRepairList = repairs;
        System.out.println("model.create = "+model.create());
        model=(DeviceModel) DeviceModel.find("byName", "iphone6").fetch().get(0);
        
        models.add(model);
        System.out.println("Models size"+models.size());
        return models;
    }

}
