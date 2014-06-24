import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.device.Manufacturer;

import org.junit.Test;

import play.test.UnitTest;

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
        CreateSimpleDeviceModelApple();
    }
	
	/*
	 * Creating Device Model without any relationship with repairs
	 */
	@Test
    public void CreateSimpleDeviceModel() {
		DeviceModel deviceModel = CreateSimpleDeviceModel("iPhone 7");
        assertTrue(deviceModel.id > 0);
        deviceModel._delete();
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
	 * Creating Device Model without any relationship with repairs
	 */

    public void CreateSimpleDeviceModelApple() {
		DeviceModel deviceModel = CreateSimpleDeviceModel("iPhone4");
        assertTrue(deviceModel.id > 0);
        
        deviceModel = CreateSimpleDeviceModel("iPhone4s");
        assertTrue(deviceModel.id > 0);
        
        deviceModel = CreateSimpleDeviceModel("iPhone5s");
        assertTrue(deviceModel.id > 0);
        
        deviceModel = CreateSimpleDeviceModel("iPhone5");
        assertTrue(deviceModel.id > 0);
        
        deviceModel = CreateSimpleDeviceModel("iPhone5C");
        assertTrue(deviceModel.id > 0);
        
        Manufacturer manufacturer = (Manufacturer) Manufacturer.find("byName", "Apple").fetch().get(0);
        manufacturer.deviceModels = DeviceModel.find("byNameLike", "iPhone%").fetch();
        manufacturer.save();
        
        assertTrue(manufacturer.deviceModels.size() > 0);
    }
	
	/*
	 * Creating Device Model without any relationship with repairs
	 */
	@Test
    public void CreateSimpleDeviceModelSamsung() {
		DeviceModel deviceModel = CreateSimpleDeviceModel("GalaxyS3");
        assertTrue(deviceModel.id > 0);
        
        deviceModel = CreateSimpleDeviceModel("GalaxyS4");
        assertTrue(deviceModel.id > 0);
        
        deviceModel = CreateSimpleDeviceModel("GalaxyS5");
        assertTrue(deviceModel.id > 0);
        
        long time = System.currentTimeMillis();
        List<DeviceModel> devices = DeviceModel.find("byNameLike", "Galaxy%").fetch();
        Manufacturer manufacturer = new Manufacturer("Samsung", "Samsung", "Samsung manufacturer from testcase", "Samsung.png", new Timestamp(time), new Timestamp(time),devices ).save();
        
        assertTrue(manufacturer.deviceModels.size() > 0);
    }
	
	/*
	 * Creating Device Repair
	 */
	@Test
    public void CreateDeviceRepair() {
		DeviceRepair deviceRepair = CreateSimpleDeviceRepair("Display");
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
		assertTrue(_CreateDeviceModelWithRepairs("Nexus5"));
		assertTrue(_CreateDeviceModelWithRepairs("iPhone4"));
		assertTrue(_CreateDeviceModelWithRepairs("iPhone5s"));
    }
	
	private boolean _CreateDeviceModelWithRepairs(String ModelName) {
        List<DeviceRepair> deviceRepairs =  new ArrayList<DeviceRepair>();
        deviceRepairs.add(CreateSimpleDeviceRepair("Display"));
        deviceRepairs.add(CreateSimpleDeviceRepair("PowerButton"));
		DeviceModel deviceModel = (DeviceModel) DeviceModel.find("byName", ModelName).fetch().get(0);
		deviceModel.deviceRepairList = deviceRepairs;
		deviceModel.save();
		return deviceModel.id > 0;
    }
	
	/*
	 * Create Device repairs then create Device model
	 * Add Device repairs to device model and save
	 * Add device models to Manufacturer and create 
	 * 
	 */
//	@Test
//    public void CreateManufacturerWithDeviceModelAndWithRepairs() {
//		List<DeviceRepair> deviceRepairs =  new ArrayList<DeviceRepair>();
//        deviceRepairs.add(CreateSimpleDeviceRepair("Display"));
//        deviceRepairs.add(CreateSimpleDeviceRepair("PowerButton"));
//		DeviceModel deviceModel =  (DeviceModel) DeviceModel.find("byName", "iPhone4").fetch().get(0);
//		deviceModel.deviceRepairList = deviceRepairs;
//		deviceModel.save();
//		assertTrue(deviceModel.id > 0 && deviceModel.deviceRepairList.size()>0);
//		
//		deviceModel =  (DeviceModel) DeviceModel.find("byName", "iPhone5s").fetch().get(0);
//		deviceModel.deviceRepairList = deviceRepairs;
//		deviceModel.save();
//		assertTrue(deviceModel.id > 0 && deviceModel.deviceRepairList.size()>0);
//    }

}
