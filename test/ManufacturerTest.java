import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.ContactInformation;
import models.Interval;
import models.Location;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.device.Manufacturer;
import models.technician.Technician;
import models.technician.WorkingHours;

import org.joda.time.DateTime;
import org.junit.Test;

import play.db.jpa.JPABase;
import play.test.UnitTest;
import utility.QueryUtil;

/**
 * 
 * @author Saqib - saqib.javed87@gmail.com
 * 
 */

public class ManufacturerTest extends UnitTest {

	/*
	 * To test if database is connected and table exits for Manufacturer
	 */
	@Test
    public void retrieveManufacturerList() {
        List<Manufacturer> manufacturer =  Manufacturer.findAll();
        assertNotNull(manufacturer);
    }
	
	/*
	 * Creating Manufacturer without any relationship with device models
	 */
	@Test
    public void CreateSimpleManufacturer() {
		long time = System.currentTimeMillis();
		Manufacturer manufacturer = new Manufacturer("Apple", "Apple", "Apple manufacturer from testcase", "apple.png", new Timestamp(time), new Timestamp(time), null).save();
        assertNotNull(manufacturer);
        assertTrue(manufacturer.id > 0);
    }
	
	/*
	 * Creating Device Model without any relationship with repairs
	 */
	@Test
    public void CreateSimpleDeviceModel() {
		DeviceModel deviceModel = CreateSimpleDeviceModel("iPhone 7");
        assertTrue(deviceModel.id > 0);
    }
	
	private DeviceModel CreateSimpleDeviceModel(String name){
		long time = System.currentTimeMillis();
		return new DeviceModel(name, name, "Test case "+name+" description", name+".png", new Timestamp(time), new Timestamp(time), null).save();
	}
	
	
	/*
	 * Create Device models and add them to Manufacturer object
	 * Then save manufacturer object and verify relationships
	 */
	@Test
    public void CreateManufacturerWithDeviceModels() {
        List<DeviceModel> devices =  new ArrayList<DeviceModel>();
        devices.add(CreateSimpleDeviceModel("Nexus4"));
        devices.add(CreateSimpleDeviceModel("Nexus5"));
		long time = System.currentTimeMillis();
		Manufacturer manufacturer = new Manufacturer("LG", "LG", "LG manufacturer from testcase", "LG.jpg", new Timestamp(time), new Timestamp(time),devices ).save();
        assertNotNull(manufacturer);
        assertTrue(manufacturer.id > 0 && manufacturer.deviceModels.size()>0);
    }
	
	/*
	 * Creating Device Repair
	 */
	@Test
    public void CreateDeviceRepair() {
		DeviceRepair deviceRepair = CreateSimpleDeviceRepair("Display");//new DeviceRepair("Display", "Display", "Test desctiption of repair", "cracked.png", (float) 60.5, 60000).save();
        assertTrue(deviceRepair.id > 0);
    }
	
	private DeviceRepair CreateSimpleDeviceRepair(String name){
		long time = System.currentTimeMillis();
		return new DeviceRepair(name, name, "Test case "+name+" description", name+".png", (float)60.8, 60000, new Timestamp(time), new Timestamp(time)).save();
	}
	
	
	/*
	 * Create Device repairs then create Device model
	 * Add Device repairs to device model and save
	 * 
	 */
	@Test
    public void CreateDeviceModelWithRepairs() {
        List<DeviceRepair> deviceRepairs =  new ArrayList<DeviceRepair>();
        deviceRepairs.add(CreateSimpleDeviceRepair("Display"));
        deviceRepairs.add(CreateSimpleDeviceRepair("PowerButton"));
		DeviceModel deviceModel = CreateSimpleDeviceModel("HTC Evo");
		deviceModel.deviceRepairList = deviceRepairs;
		deviceModel.save();
		assertTrue(deviceModel.deviceRepairList.size()>0);
    }
	
	/*
	 * Create Device repairs then create Device model
	 * Add Device repairs to device model and save
	 * Add device models to Manufacturer and create 
	 * 
	 */
	@Test
    public void CreateManufacturerWithDeviceModelAndWithRepairs() {
		List<DeviceRepair> deviceRepairs =  new ArrayList<DeviceRepair>();
		List<DeviceModel> deviceModels =  new ArrayList<DeviceModel>();
        deviceRepairs.add(CreateSimpleDeviceRepair("Display"));
        deviceRepairs.add(CreateSimpleDeviceRepair("PowerButton"));
		DeviceModel deviceModel = CreateSimpleDeviceModel("HTC Evo");
		deviceModel.deviceRepairList = deviceRepairs;
		deviceModel.save();
		deviceModels.add(deviceModel);
        long time = System.currentTimeMillis();
		Manufacturer manufacturer = new Manufacturer("HTC", "HTC", "HTC manufacturer from testcase", "htc.png", new Timestamp(time), new Timestamp(time),deviceModels ).save();
		assertTrue(deviceModel.deviceRepairList.size()>0);
		assertNotNull(manufacturer);
        assertTrue(manufacturer.id > 0 && manufacturer.deviceModels.size()>0);
		
    }

}
